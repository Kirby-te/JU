import sqlite3
import college
class StudentDB:    
    def __init__(self, db_path = './database/students.db'):
        self.db_path = db_path
        self.connection = sqlite3.connect(self.db_path)
        self.cursor = self.connection.cursor()
        self.create_table()
        
    def create_table(self):
        self.cursor.execute("""CREATE TABLE IF NOT EXISTS
            students
            (
                rollNo INTEGER PRIMARY KEY,
                email TEXT UNIQUE,
                password TEXT NOT NULL,
                first_name TEXT NOT NULL,
                last_name TEXT,
                phoneNo TEXT
            )
            """)
        self.connection.commit()
        
    
    def add_student(self, email: str, password: str, first_name: str, last_name: str, phoneNo: str) -> str:
        if (not email) or (not password) or (not first_name):
            return "One or more fields, entered are blanks."
        try:
            self.cursor.execute("INSERT INTO students (password, first_name, last_name, email, phoneNo) VALUES (?, ?, ?, ?, ?)",
                                (password, first_name, last_name, email, phoneNo))
            self.connection.commit()
            return "Student added successfully.\nLogin with Email."
        except sqlite3.IntegrityError:
            return "Email already exists.\nPlease provide unique details."

    def update_student(self, rollNo: int, new_email: str = None, new_password: str = None, new_first_name: str = None, new_last_name: str = None, new_phoneNo: str = None) -> str:
        try:
            query = "UPDATE students SET "
            params = []
            
            if new_email is not None:
                query += "email=?, "
                params.append(new_email)
            
            if new_phoneNo is not None:
                query += "phoneNo=?, "
                params.append(new_phoneNo)
            
            if new_password is not None:
                query += "password=?, "
                params.append(new_password)
            
            if new_first_name is not None:
                query += "first_name=?, "
                params.append(new_first_name)
            
            if new_last_name is not None:
                query += "last_name=?, "
                params.append(new_last_name)
            
            query = query.rstrip(", ")
            
            query += " WHERE rollNo=?"
            params.append(rollNo)
            
            self.cursor.execute(query, tuple(params))
            self.connection.commit()
            
            return "Updated successfully."
        
        except sqlite3.Error as e:
            print("Error occurred:", e)
            return "Error occurred while updating student."

    def remove_student(self, rollNo: int) -> str:
        try:
            self.cursor.execute("DELETE FROM students WHERE rollNo=?", (rollNo,))
            self.connection.commit()
            return "Student removed successfully."
        except sqlite3.Error as e:
            print("Error occurred:", e)
            
    def email_exists(self, email: str) -> bool:
        self.cursor.execute("SELECT * FROM students WHERE email=?", (email,))
        if self.cursor.fetchone():
            return True
        else:
            return False

    def roll_exists(self, rollNo: int) -> bool:
        self.cursor.execute("SELECT * FROM students WHERE rollNo=?", (rollNo,))
        if self.cursor.fetchone():
            return True
        else:
            return False
        
    def get_password(self, identifier: str) -> str:
        query = "SELECT password FROM students WHERE email = ? OR rollNo = ?"
        self.cursor.execute(query, (identifier, identifier))
        result = self.cursor.fetchone()
        if result:
            return result[0]
        else:
            return None
        
    def get_details(self, identifier):
        query = "SELECT rollNo, email, password, first_name, last_name, phoneNo FROM students WHERE email = ? OR rollNo = ?"
        self.cursor.execute(query, (identifier, identifier))
        
        user = self.cursor.fetchone()
        if user:
            return user
        return []
    
    def drop_table(self):
        try:
            self.cursor.execute("DROP TABLE IF EXISTS students")
            self.connection.commit()
            print("Table 'students' dropped successfully.")
        except sqlite3.Error as e:
            print("Error occurred while dropping table 'students':", e)

    def close_connection(self):
        self.connection.close()



class MarkDB:
    def __init__(self, db_path='./database/students.db', college_db_path='./database/college.db', subjects_table_name='subjects', grades_table_name='grades'):
        self.db_path = db_path
        self.college_db_path = college_db_path
        self.subjects_table_name = subjects_table_name
        self.grades_table_name = grades_table_name
        self.connection = sqlite3.connect(self.db_path)
        self.cursor = self.connection.cursor()
        self.create_table()
        self.attach_college_database()

    def create_table(self):
        self.cursor.execute("""CREATE TABLE IF NOT EXISTS
            marks
            (
                rollNo INTEGER NOT NULL,
                subject_id INTEGER NOT NULL,
                mark INTEGER,
                grade TEXT,
                PRIMARY KEY (rollNo, subject_id),
                FOREIGN KEY (rollNo) REFERENCES students(rollNo),
                FOREIGN KEY (subject_id) REFERENCES {}(subject_id),
                FOREIGN KEY (grade) REFERENCES {}(grade)
            )
            """.format(self.subjects_table_name, self.grades_table_name))
        self.connection.commit()

    def attach_college_database(self):
        self.cursor.execute(f"ATTACH DATABASE '{self.college_db_path}' AS college")
        self.connection.commit()
        
    def calculateGrade(self, mark: int) -> str:
        try:
            self.cursor.execute(f"SELECT * FROM {self.grades_table_name} WHERE ? >= lower_bound AND ? <= upper_bound",
                                (mark, mark))
            grade = self.cursor.fetchone()

            if grade:
                return grade[1]
            else:
                return None
        except sqlite3.Error as e:
            print("Error occurred while calculating grade:", e)
            return None
    
    def add_mark(self, rollNo: int, subject_id: int, mark: int) -> str:
        if mark < 0 or mark > 100:
            print("Mark not valid")
            return "Mark not valid"
        
        stud = StudentDB()
        if not stud.roll_exists(rollNo):
            print("Student doesn't exist.")
            return "Student doesn't exist."
        stud.close_connection()
        
        sub = college.SubjectDB()
        if not sub.subject_exists(subject_id):
            print("Subject doesn't exist.")
            return "Subject doesn't exist."
        sub.close_connection()
        
        if (not rollNo) or (not subject_id):
            return "One or more fields, entered are blanks."
        try:
            grade = self.calculateGrade(mark)
            self.cursor.execute("INSERT INTO marks VALUES(?, ?, ?, ?)",
                                (rollNo, subject_id, mark, grade))
            self.connection.commit()
            return "Mark added successfully."
        except sqlite3.Error as e:
            print("Error occurred:", e)
    
    def update_mark(self, rollNo: int, subject_id: int, mark: int) -> str:
        if mark < 0 or mark > 100:
            print("Mark not valid")
            return "Mark not valid"
        
        stud = StudentDB()
        if not stud.roll_exists(rollNo):
            print("Student doesn't exist.")
            return "Student doesn't exist."
        stud.close_connection()
        
        sub = college.SubjectDB()
        if not sub.subject_exists(subject_id):
            print("Subject doesn't exist.")
            return "Subject doesn't exist."
        sub.close_connection()
        
        if (not rollNo) or (not subject_id):
            return "One or more fields, entered are blanks."
        try:
            grade = self.calculateGrade(mark)
            self.cursor.execute("UPDATE marks SET mark=?, grade=? WHERE rollNo=? AND subject_id=?",
                                (mark, grade, rollNo, subject_id))
            self.connection.commit()
            return "Mark changed successfully."
        except sqlite3.Error as e:
            print("Error occurred:", e)
            
    def remove_grade(self, rollNo: int) -> str:
        try:
            self.cursor.execute("DELETE FROM marks WHERE rollNo=?", (rollNo,))
            self.connection.commit()
            return "Student removed successfully."
        except sqlite3.Error as e:
            print("Error occurred:", e)
            
    def drop_table(self):
        try:
            self.cursor.execute("DROP TABLE IF EXISTS marks")
            self.connection.commit()
            print("Table 'marks' dropped successfully.")
        except sqlite3.Error as e:
            print("Error occurred while dropping table 'marks':", e)

    def close_connection(self):
        self.connection.close()



class ResultDB:
    def __init__(self, db_path='./database/students.db', college_db_path='./database/college.db', subjects_table_name='subjects', grades_table_name='grades'):
        self.db_path = db_path
        self.college_db_path = college_db_path
        self.subjects_table_name = subjects_table_name
        self.grades_table_name = grades_table_name
        self.connection = sqlite3.connect(self.db_path)
        self.cursor = self.connection.cursor()
        self.create_table()
        self.attach_college_database()
    
    def attach_college_database(self):
        self.cursor.execute(f"ATTACH DATABASE '{self.college_db_path}' AS college")
        self.connection.commit()

    def create_table(self):
        su = college.SubjectDB()
        subs = su.get_subject_names()
        query = ""
        query += "CREATE TABLE IF NOT EXISTS \nresults(\nrollNo INTEGER NOT NULL,\n "
        for sub in subs:
            query += f"{sub} TEXT, "
        query += "GPA FLOAT, "
        query += "\nPRIMARY KEY (rollNo)"
        query += ", FOREIGN KEY (rollNo) REFERENCES students(rollNo))"
        self.cursor.execute(query)
        self.connection.commit()
        
    def calculate_gpa(self, grades: list) -> float:
        total_points = 0
        total_credits = len(grades)
        for grade in grades:
            if grade == 'S':
                total_points += 7.0
            elif grade == 'A':
                total_points += 6.0
            elif grade == 'B':
                total_points += 5.0
            elif grade == 'C':
                total_points += 4.0
            elif grade == 'D':
                total_points += 3.0
            elif grade == 'E':
                total_points += 2.0
            elif grade == 'F':
                total_points += 1.0

        cumulative_gpa = total_points / total_credits
        gpa = cumulative_gpa * (10 / 7)
        return round(gpa, 2)
        
    def add_result(self, rollNo: int, grades: list) -> str:
        try:
            stud1 = StudentDB()
            if not stud1.roll_exists(rollNo):
                print("Student doesn't exist")
                return "Student doesn't exist"
            stud1.close_connection()
            
            mark_placeholders = ', '.join(['?' for _ in range(len(grades))])
            gpa = self.calculate_gpa(grades)
            query = f"INSERT INTO results VALUES (?, {mark_placeholders}, ?)"
            print(query)
            self.cursor.execute(query, [rollNo] + grades + [str(gpa)])
            self.connection.commit()
            print("Result added successfully.")
            return "Result added successfully."
        except sqlite3.Error as e:
            print("Error occurred while adding result:", e)
            
    def remove_result(self, rollNo: int) -> str:
        try:
            self.cursor.execute("DELETE FROM results WHERE rollNo=?", (rollNo,))
            self.connection.commit()
            return "Result removed successfully."
        except sqlite3.Error as e:
            print("Error occurred:", e)
            
    def drop_table(self):
        try:
            self.cursor.execute("DROP TABLE IF EXISTS results")
            self.connection.commit()
            print("Table 'results' dropped successfully.")
        except sqlite3.Error as e:
            print("Error occurred while dropping table 'results':", e)

    def close_connection(self):
        self.connection.close()
