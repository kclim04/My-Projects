from flask import Flask, render_template,redirect,url_for,request,session
from flask_mysqldb import MySQL
import datetime

app = Flask(__name__)
app.secret_key = 'your_secret_key'

# database configuration
app.config['MYSQL_HOST'] = '127.0.0.1'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = ''
app.config['MYSQL_DB'] = 'py'

mysql = MySQL(app)

def user_is_logged_in():
    return session.get('user_is_logged_in', False)

@app.route('/')
def home():
    if not user_is_logged_in():
        return redirect(url_for('login'))
    # If the user is logged in, render the home page
    return redirect(url_for('attendance'))

@app.route('/login', methods = ['POST', 'GET'])
def login():
    if request.method == 'GET':
        user_role = session.get('role', None)
        if not user_is_logged_in() or user_role is None:
            return render_template('login.html', error='')
        else:
            if user_role == 'student':
                return redirect(url_for('attendance'))
            else:
                return redirect(url_for('lec_attendance'))
            
    if request.method == 'POST':
        email = request.form['email']
        password = request.form['password']
        role = request.form['role']
        if role == 'student':
            cursor = mysql.connection.cursor()
            cursor.execute('SELECT * FROM student WHERE email = %s', (email,))
            student = cursor.fetchone()
            cursor.close()
            if student and password == student[3]:
                session['user_is_logged_in'] = True
                session['id'] = student[0]
                session['role'] = 'student'
                return redirect(url_for('attendance'))
            else:
                return render_template('login.html', error='Account or Password Invalid!')
        elif role == 'lecturer':
            cursor = mysql.connection.cursor()
            cursor.execute('SELECT * FROM lecturer WHERE email = %s', (email,))
            lecturer = cursor.fetchone()
            cursor.close()
            if lecturer and password == lecturer[3]:
                session['user_is_logged_in'] = True
                session['id'] = lecturer[0]
                session['role'] = 'lecturer'
                return redirect(url_for('lec_attendance'))
            else:
                return render_template('login.html', error='Account or Password Invalid!')
        else:
            return render_template('login.html', error='Role Not Found!')
        

## STUDENT VIEW ##
@app.route('/attendance')
def attendance():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    cursor = mysql.connection.cursor()
    cursor.execute('SELECT attendance.class_id,attendance.checkin FROM py.attendance LEFT JOIN py.class ON class.id = attendance.class_id LEFT JOIN py.subject ON subject.id = class.subject_id WHERE attendance.student_id = %s',(user_id,))
    classCode = cursor.fetchall()
    cursor.close()

    cursor = mysql.connection.cursor()
    cursor.execute('SELECT class.start_time, subject.subject_code, attendance.status, attendance.updated FROM `attendance` LEFT JOIN class ON class.id = attendance.class_id LEFT JOIN `subject` ON `subject`.id = class.subject_id WHERE attendance.student_id = %s',(user_id,))
    attendaceHistory = cursor.fetchall()
    cursor.close()

    cursor = mysql.connection.cursor()
    cursor.execute('SELECT count(*) FROM `attendance`  LEFT JOIN class ON class.id = attendance.class_id LEFT JOIN `subject` ON `subject`.id = class.subject_id WHERE attendance.student_id = %s',(user_id,))
    classTotal = cursor.fetchone()
    cursor.close()

    cursor = mysql.connection.cursor()
    cursor.execute('SELECT (count(*) / %s) * 100 as total FROM `attendance` LEFT JOIN class ON class.id = attendance.class_id LEFT JOIN `subject` ON `subject`.id = class.subject_id WHERE attendance.student_id = %s AND attendance.status = 2',(classTotal,user_id,))
    presentClassTotal = cursor.fetchone()
    cursor.close()

    cursor = mysql.connection.cursor()
    cursor.execute('SELECT (count(*) / %s) * 100 as total FROM `attendance` LEFT JOIN class ON class.id = attendance.class_id LEFT JOIN `subject` ON `subject`.id = class.subject_id WHERE attendance.student_id = %s AND attendance.status = 3',(classTotal,user_id,))
    lateClassTotal = cursor.fetchone()
    cursor.close()

    cursor = mysql.connection.cursor()
    cursor.execute('SELECT (count(*) / %s) * 100 as total FROM `attendance` LEFT JOIN class ON class.id = attendance.class_id LEFT JOIN `subject` ON `subject`.id = class.subject_id WHERE attendance.student_id = %s AND attendance.status = 4',(classTotal,user_id,))
    absentClassTotal = cursor.fetchone()
    cursor.close()
    

    return render_template('attendance.html',role = 1,percentAttend=presentClassTotal[0],percentAbsence=absentClassTotal[0],percentLate=lateClassTotal[0],attendaceHistory=attendaceHistory,subjectList=classCode)

@app.route('/clockin', methods = ['POST', 'GET'])
def clockin():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    
    if request.method == 'POST':
        ## Pass from HTML
        attendanceId = request.form['attendanceId']
        attendanceStartTime_str = request.form['attendanceStartTime']
        attendanceEndTime_str = request.form['attendanceEndTime']

        attendanceStartTime = datetime.datetime.strptime(attendanceStartTime_str, '%Y-%m-%d %H:%M:%S')
        attendanceEndTime = datetime.datetime.strptime(attendanceEndTime_str, '%Y-%m-%d %H:%M:%S')

        print(attendanceId)
        print(attendanceStartTime) # look like 2024-03-18 10:00:00 #
        print(attendanceEndTime)

        current_time = datetime.datetime.now()
        print(current_time)

        time_difference = current_time - attendanceStartTime # Calculate the difference between current_time and attendanceStartTime

        fifteen_minutes = datetime.timedelta(minutes=15) # Define a timedelta object representing 15 minutes

        
        if time_difference > fifteen_minutes and current_time < attendanceEndTime :
            print('Late')
            cursor = mysql.connection.cursor()
            cursor.execute("UPDATE attendance SET status = '3' WHERE class_id = %s AND student_id = %s ", (attendanceId,user_id)) # Update query for status late where user checkin after 15minutes
            mysql.connection.commit()
            cursor.close()
        elif current_time < attendanceStartTime:
            print('Pending, please wait until your class start')
            cursor = mysql.connection.cursor()
            cursor.execute("UPDATE attendance SET status = '1' WHERE class_id = %s AND student_id = %s ", (attendanceId,user_id))# Update query for status pending where user checkin before class time
            mysql.connection.commit()
            cursor.close()
        elif current_time > attendanceEndTime:
            print('Absent')
            cursor = mysql.connection.cursor()
            cursor.execute("UPDATE attendance SET status = '4' WHERE class_id = %s AND student_id = %s ", (attendanceId,user_id))# Update query for status absent where user never checkin
            mysql.connection.commit()
            cursor.close()
        else:
            print('Present')
            cursor = mysql.connection.cursor()
            cursor.execute("UPDATE attendance SET status = '2' WHERE class_id = %s AND student_id = %s ", (attendanceId,user_id))# Update query for status present where user checkin in time
            mysql.connection.commit()
            cursor.close()

        return redirect(url_for('attendance'))
    
@app.route('/getSubjectAttendace', methods = ['POST', 'GET'])
def getSubjectAttendace():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    cursor = mysql.connection.cursor()
    cursor.execute('SELECT attendance.class_id,attendance.checkin FROM py.attendance LEFT JOIN py.class ON class.id = attendance.class_id LEFT JOIN py.subject ON subject.id = class.subject_id WHERE attendance.student_id = %s',(user_id,))
    classCode = cursor.fetchall()
    cursor.close()
    ## Pass from HTML
    subjectID = request.form['subject']
    print(subjectID)

    cursor = mysql.connection.cursor()
    cursor.execute('SELECT subject.name, subject.subject_code, attendance.checkin, class.start_time,class.end_time,attendance.id from attendance LEFT JOIN class ON class.id = attendance.class_id LEFT JOIN subject ON subject.id = class.subject_id WHERE class.id = %s AND attendance.student_id = %s AND attendance.status = 1',(subjectID, user_id)) 
    attendanceList = cursor.fetchone()
    cursor.close()

    cursor = mysql.connection.cursor()
    cursor.execute('SELECT class.start_time, subject.subject_code, attendance.status, attendance.updated FROM `attendance` LEFT JOIN class ON class.id = attendance.class_id LEFT JOIN `subject` ON `subject`.id = class.subject_id WHERE attendance.student_id = %s AND attendance.student_id = 1',(user_id,))
    attendaceHistory = cursor.fetchall()
    cursor.close()

    cursor = mysql.connection.cursor()
    cursor.execute('SELECT count(*) FROM `attendance`  LEFT JOIN class ON class.id = attendance.class_id LEFT JOIN `subject` ON `subject`.id = class.subject_id WHERE attendance.student_id = %s',(user_id,))
    classTotal = cursor.fetchone()
    cursor.close()

    cursor = mysql.connection.cursor()
    cursor.execute('SELECT (count(*) / %s) * 100 as total FROM `attendance` LEFT JOIN class ON class.id = attendance.class_id LEFT JOIN `subject` ON `subject`.id = class.subject_id WHERE attendance.student_id = %s AND attendance.status = 2',(classTotal,user_id,))
    presentClassTotal = cursor.fetchone()
    cursor.close()

    cursor = mysql.connection.cursor()
    cursor.execute('SELECT (count(*) / %s) * 100 as total FROM `attendance` LEFT JOIN class ON class.id = attendance.class_id LEFT JOIN `subject` ON `subject`.id = class.subject_id WHERE attendance.student_id = %s AND attendance.status = 3',(classTotal,user_id,))
    lateClassTotal = cursor.fetchone()
    cursor.close()

    cursor = mysql.connection.cursor()
    cursor.execute('SELECT (count(*) / %s) * 100 as total FROM `attendance` LEFT JOIN class ON class.id = attendance.class_id LEFT JOIN `subject` ON `subject`.id = class.subject_id WHERE attendance.student_id = %s AND attendance.status = 4',(classTotal,user_id,))
    absentClassTotal = cursor.fetchone()
    cursor.close()

    return render_template('attendance.html', role = 1,attendanceList = attendanceList,percentAttend=presentClassTotal[0],percentAbsence=absentClassTotal[0],percentLate=lateClassTotal[0],attendaceHistory=attendaceHistory,subjectList=classCode ,selectedSubject=subjectID)
    
@app.route('/assignment')
def assignment():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    ## to detect did redirect pass error 
    error = request.args.get('error')
    cursor = mysql.connection.cursor()
    cursor.execute("SELECT subject.subject_code, assignment_main.assignment_title, assignment_main.submission_due_date, assignment_submit.status,assignment_submit.id FROM subject LEFT JOIN assignment_main ON subject.id=assignment_main.subject_id LEFT JOIN assignment_submit ON assignment_main.id=assignment_submit.assignment_main_id WHERE assignment_submit.student_id = %s",(user_id,))
    studentAssignment = cursor.fetchall()
    cursor.close()
    return render_template('assignment.html',role = 1, studentAssignment=studentAssignment,error=error)

@app.route('/assignmentURL', methods = ['GET','POST'])
def assignmentURL():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    
    assignmentLink = request.form['assignmentLink']
    asssignmentID = request.form['assignmentID']

    if assignmentLink == "":
        return redirect(url_for('assignment',error='Please Fill in the Assignment LINK!!!'))

    ## Due Date Checking here
    cursor = mysql.connection.cursor()
    cursor.execute('SELECT assignment_main.submission_due_date FROM py.assignment_main LEFT JOIN py.assignment_submit ON assignment_submit.assignment_main_id = assignment_main.id WHERE assignment_submit.id = %s',(asssignmentID,))
    dueDate = cursor.fetchone()
    cursor.close()

    print(dueDate[0])

    current_time = datetime.datetime.now()

    if current_time < dueDate[0] :
        print('Submit')
        cursor = mysql.connection.cursor()
        cursor.execute("UPDATE assignment_submit SET url = %s, status = 2 WHERE id = %s",(assignmentLink,asssignmentID))# Update query for status submit where user submit before due date
        mysql.connection.commit()
        cursor.close()
    else:
        print('Late Submit')
        cursor = mysql.connection.cursor()
        cursor.execute("UPDATE assignment_submit SET url = %s, status = 3 WHERE id = %s ", (assignmentLink,asssignmentID)) # Update query for status late submit where user submit after due date
        mysql.connection.commit()
        cursor.close()

    return redirect(url_for('assignment'))
    
@app.route('/timetable')
def timetable():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    cursor = mysql.connection.cursor()
    cursor.execute("SELECT subject.subject_code, subject.name, lecturer.name, venue.name, class.start_time FROM subject_enrol LEFT JOIN subject ON subject_enrol.subject_id=subject.id LEFT JOIN lecturer ON subject.lecturer_id=lecturer.id LEFT JOIN class ON lecturer.id=class.lecturer_id LEFT JOIN venue ON class.venue_id=venue.id WHERE subject_enrol.student_id = %s", (user_id,))
    studentTimetable = cursor.fetchall()
    cursor.close()
    return render_template('timetable.html',role = 1, studentTimetable=studentTimetable)


## LECTURER VIEW ##
@app.route('/lec_attendance')
def lec_attendance():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    cursor = mysql.connection.cursor()
    cursor.execute("SELECT class.id, class.start_time, attendance.student_id, student.name, attendance.status, attendance.id FROM class LEFT JOIN attendance ON class.start_time=attendance.created LEFT JOIN student ON attendance.student_id=student.id WHERE class.lecturer_id = %s ORDER BY class.id,student.id ", (user_id,))
    lecturerAttendance = cursor.fetchall()
    cursor.close()
    return render_template('lec_attendance.html',role = 2, lecturerAttendance=lecturerAttendance)

#Lecturer mark attendance
@app.route('/lec_present', methods = ['POST', 'GET'])
def lec_present():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    attendanceID = request.form['lecattendanceID']
    print(attendanceID)
    cursor = mysql.connection.cursor()
    cursor.execute("UPDATE attendance SET status = 2 WHERE id = %s", (attendanceID,))
    mysql.connection.commit()
    attendancepresent = cursor.fetchall()
    cursor.close()
    return redirect(url_for('lec_attendance', attendancepresent=attendancepresent))

@app.route('/lec_absent', methods = ['POST', 'GET'])
def lec_absent():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    attendanceID = request.form['lecattendanceID']
    print(attendanceID)
    cursor = mysql.connection.cursor()
    cursor.execute("UPDATE attendance SET status = 4 where id = %s", (attendanceID,))
    mysql.connection.commit()
    attendanceabsent = cursor.fetchall()
    cursor.close()
    return redirect(url_for('lec_attendance', attendanceabsent=attendanceabsent))

@app.route('/lec_assignment')
def lec_assignment():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    
    cursor = mysql.connection.cursor()
    cursor.execute("SELECT subject.subject_code, student.name, assignment_main.subject_id, assignment_main.created_date, assignment_main.created_time, assignment_submit.status, assignment_submit.id, assignment_submit.url FROM subject LEFT JOIN assignment_main ON subject.id=assignment_main.subject_id LEFT JOIN assignment_submit ON assignment_main.subject_id=assignment_submit.assignment_main_id LEFT JOIN student ON assignment_submit.student_id=student.id WHERE subject.lecturer_id = %s", (user_id,))
    lecturerAssignment = cursor.fetchall()
    cursor.close()
    return render_template('lec_assignment.html',role = 2, lecturerAssignment=lecturerAssignment)

# Lec marking for GRADE PASS or FAIL
@app.route('/lec_pass', methods = ['POST', 'GET'])
def lec_pass():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    assignmentID = request.form['lecassignmentID']
    print(assignmentID)
    cursor = mysql.connection.cursor()
    cursor.execute("UPDATE assignment_submit SET status = 4 where id = %s", (assignmentID,))
    mysql.connection.commit()
    gradepass = cursor.fetchall()
    cursor.close()
    return redirect(url_for('lec_assignment', gradepass=gradepass))

@app.route('/lec_fail', methods = ['POST', 'GET'])
def lec_fail():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    assignmentID = request.form['lecassignmentID']
    cursor = mysql.connection.cursor()
    cursor.execute("UPDATE assignment_submit SET status = 5 WHERE id = %s", (assignmentID,))
    mysql.connection.commit()
    gradefail = cursor.fetchall()
    cursor.close()
    return redirect(url_for('lec_assignment', gradefail=gradefail))

@app.route('/lec_timetable')
def lec_timetable():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    cursor = mysql.connection.cursor()
    cursor.execute("SELECT subject.subject_code, class.start_time, venue.name, class.id, class.end_time, class.subject_id, class.venue_id FROM subject LEFT JOIN class ON subject.id=class.subject_id LEFT JOIN venue ON class.venue_id=venue.id WHERE class.lecturer_id = %s",(user_id,))
    lecturerTimetable = cursor.fetchall()
    cursor.close()

    cursor = mysql.connection.cursor()
    cursor.execute("SELECT * FROM venue")
    venueList = cursor.fetchall()
    cursor.close()

    cursor = mysql.connection.cursor()
    cursor.execute("SELECT * FROM subject WHERE lecturer_id = %s", (user_id,))
    subjectList = cursor.fetchall()
    cursor.close()

    return render_template('lec_timetable.html',role = 2, lecturerTimetable=lecturerTimetable,subjectList=subjectList,venueList=venueList)

# Lec UPDATE timetable
@app.route('/lec_update', methods = ['POST', 'GET'])
def lec_update():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    
    classID = request.form['classID']
    subjectIdEdit = request.form['subjectIdEdit']
    startDateEdit = request.form['startDateEdit']
    endDateEdit = request.form['endDateEdit']
    venueEdit = request.form['venueEdit']


    cursor = mysql.connection.cursor()
    cursor.execute("UPDATE class SET subject_id = %s, start_time = %s, end_time = %s, venue_id = %s where id = %s", (subjectIdEdit, startDateEdit, endDateEdit, venueEdit, classID))
    mysql.connection.commit()
    cursor.close()

    return redirect(url_for('lec_timetable'))


# Lec DELETE timetable
@app.route('/lec_delete', methods = ['POST', 'GET'])
def lec_delete():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))

    classID = request.form['classID']

    cursor = mysql.connection.cursor()
    cursor.execute("DELETE FROM class WHERE id = %s", (classID,))
    mysql.connection.commit()

    return redirect(url_for('lec_timetable'))

# Lec CREATE timetable
@app.route('/lec_create', methods = ['POST', 'GET'])
def lec_create():
    user_role = session.get('role', None)
    user_id = session.get('id', None)
    if not user_is_logged_in() or user_role is None:
        return redirect(url_for('login'))
    
    subjectId = request.form['subjectId']
    startDate = request.form['startDate']
    endDate = request.form['endDate']
    venue = request.form['venue']
    print(startDate)
    cursor = mysql.connection.cursor()
    query = "INSERT INTO class (venue_id, start_time, end_time, lecturer_id, subject_id) VALUES (%s, %s, %s, %s, %s)"
    values = (venue, startDate, endDate, user_id, subjectId)
    print("Query:", query)
    print("Values:", values)
    cursor.execute(query, values)
    mysql.connection.commit()
    cursor.close()

    return redirect(url_for('lec_timetable'))


@app.route('/logout' , methods = ['POST', 'GET'])
def logout():
    session.clear()
    return redirect(url_for('login'))

if __name__ == '__main__':
    app.run(debug=True,port=5001)