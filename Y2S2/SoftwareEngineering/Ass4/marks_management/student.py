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
                password TEXT NOT NULL,
                first_name TEXT NOT NULL,
                last_name TEXT,
                email TEXT UNIQUE,
                phoneNo TEXT
            )
            """)
        self.connection.commit()
    
    def add_student(self, password, first_name, last_name, email, phoneNo):
        try:
            self.cursor.execute("INSERT INTO students (password, first_name, last_name, email, phoneNo) VALUES (?, ?, ?, ?, ?)",
                                (password, first_name, last_name, email, phoneNo))
            self.connection.commit()
            print("Student added successfully.")
        except sqlite3.IntegrityError:
            print("Email already exists. Please provide unique details.")

    def update_student(self, rollNo, new_email, new_phoneNo):
        try:
            self.cursor.execute("UPDATE students SET email=?, phoneNo=? WHERE rollNo=?",
                                (new_email, new_phoneNo, rollNo))
            self.connection.commit()
            print("Student updated successfully.")
        except sqlite3.Error as e:
            print("Error occurred:", e)

    def remove_student(self, rollNo):
        try:
            self.cursor.execute("DELETE FROM students WHERE rollNo=?", (rollNo,))
            self.connection.commit()
            print("Student removed successfully.")
        except sqlite3.Error as e:
            print("Error occurred:", e)

    def close_connection(self):
        self.connection.close()



class MarkDB:
    def __init__(self, db_path='./database/students.db', subjects_db_path='./database/college.db', subjects_table_name='subjects'):
        self.db_path = db_path
        self.subjects_db_path = subjects_db_path
        self.subjects_table_name = subjects_table_name
        self.connection = sqlite3.connect(self.db_path)
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

    def add_mark(self, rollNo, subject_id, mark):
        try:
            self.cursor.execute("INSERT INTO marks VALUES(?, ?, ?)",
                                (rollNo, subject_id, mark))
            self.connection.commit()
            print("Mark added successfully.")
        except sqlite3.Error as e:
            print("Error occurred:", e)
    
    def update_mark(self, rollNo, subject_id, mark):
        try:
            self.cursor.execute("UPDATE marks SET mark=? WHERE rollNo=? AND subject_id=?",
                                (mark, rollNo, subject_id))
            self.connection.commit()
            print("Mark changed successfully.")
        except sqlite3.Error as e:
            print("Error occurred:", e)

    def close_connection(self):
        self.connection.close()
        


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