import sqlite3

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

    def update_student(self, rollNo: int, new_email: str, new_phoneNo: str) -> str:
        try:
            self.cursor.execute("UPDATE students SET email=?, phoneNo=? WHERE rollNo=?",
                                (new_email, new_phoneNo, rollNo))
            self.connection.commit()
            return "Student updated successfully."
        except sqlite3.Error as e:
            print("Error occurred:", e)

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
            self.cursor.execute(f"SELECT grade FROM {self.grades_table_name} WHERE ? >= lower_bound AND ? <= upper_bound",
                                (mark, mark))
            grade = self.cursor.fetchone()

            if grade:
                return grade[0]
            else:
                return None
        except sqlite3.Error as e:
            print("Error occurred while calculating grade:", e)
            return None

    def add_mark(self, rollNo: int, subject_id: int, mark: int) -> str:
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
        if (not rollNo) or (not subject_id) or (not mark):
            return "One or more fields, entered are blanks."
        try:
            grade = self.calculateGrade(mark)
            self.cursor.execute("UPDATE marks SET mark=?, grade=? WHERE rollNo=? AND subject_id=?",
                                (mark, grade, rollNo, subject_id))
            self.connection.commit()
            return "Mark changed successfully."
        except sqlite3.Error as e:
            print("Error occurred:", e)

    def close_connection(self):
        self.connection.close()



'''
class ResultDB:
    def __init__(self, db_path='./database/students.db'):
        self.db_path = db_path
        self.cursor = self.connection.cursor()
        self.create_tables()
        self.attach_subjects_database()

    def create_tables(self):
        self.cursor.execute("""CREATE TABLE IF NOT EXISTS
            marks
            (
                rollNo INTEGER,
                subject_id INTEGER,
                mark INTEGER,
                PRIMARY KEY (rollNo, subject_id),
                FOREIGN KEY (rollNo) REFERENCES students(rollNo),
                FOREIGN KEY (subject_id) REFERENCES {}(subject_id)
            )
            """.format(self.subjects_table_name))
        self.connection.commit()

    def attach_subjects_database(self):
        self.cursor.execute(f"ATTACH DATABASE '{self.subjects_db_path}' AS subjects")
        self.connection.commit()

    def close_connection(self):
        self.connection.close()
'''