import sqlite3

class SubjectDB:
    def __init__(self, db_path = './database/college.db'):
        self.db_path = db_path
        self.connection = sqlite3.connect(self.db_path)
        self.cursor = self.connection.cursor()
        self.create_table()
    
    def create_table(self):
        self.cursor.execute("""CREATE TABLE IF NOT EXISTS
            subjects
            (
                subjectId INTEGER PRIMARY KEY,
                subjectName TEXT UNIQUE NOT NULL
            )
            """)
        self.connection.commit()
    
    def add_subject(self, subjectName):
        try:
            self.cursor.execute("INSERT INTO subjects (subjectName) VALUES (?)",
                                (subjectName))
            self.connection.commit()
            print("Subject added successfully.")
        except sqlite3.IntegrityError:
            print("Subejct already exists. Please provide unique deatils.")
            
    def close_connection(self):
        self.connection.close()
        


class GradeDB:
    def __init__(self, db_path='./database/college.db'):
        self.db_path = db_path
        self.connection = sqlite3.connect(self.db_path)
        self.cursor = self.connection.cursor()
        self.create_table()
    
    def create_table(self):
        self.cursor.execute("""CREATE TABLE IF NOT EXISTS
            grades
            (
                gradeId INTEGER PRIMARY KEY,
                grade TEXT UNIQUE NOT NULL,
                lower_bound INTEGER UNIQUE NOT NULL,
                upper_bound INTEGER UNIQUE NOT NULL
            )
            """)
        self.connection.commit()
    
    def add_grade(self, grade, lower_bound, upper_bound):
        try:
            self.cursor.execute("INSERT INTO grades (grade, lower_bound, upper_bound) VALUES (?, ?, ?)",
                                (grade, lower_bound, upper_bound))
            self.connection.commit()
            print("Grade added successfully.")
        except sqlite3.IntegrityError:
            print("Grade already exists. Please provide unique details.")
            
    def close_connection(self):
        self.connection.close()