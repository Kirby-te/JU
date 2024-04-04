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
                subject_id INTEGER PRIMARY KEY,
                subject_name TEXT UNIQUE NOT NULL
            )
            """)
        self.connection.commit()
    
    def add_subject(self, subject_name: str) -> str:
        if not subject_name:
            return "One or more fields, entered are blanks."
        try:
            self.cursor.execute("INSERT INTO subjects (subject_name) VALUES (?)",
                                (subject_name,))
            self.connection.commit()
            return "Subject added successfully."
        except sqlite3.IntegrityError:
            return "Subejct already exists.\nPlease provide unique deatils."
            
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
    
    def add_grade(self, grade: str, lower_bound: int, upper_bound: int) -> str:
        if (not grade) or (not lower_bound) or (not upper_bound):
            return "One or more fields, entered are blanks."
        try:
            self.cursor.execute("INSERT INTO grades (grade, lower_bound, upper_bound) VALUES (?, ?, ?)",
                                (grade, lower_bound, upper_bound))
            self.connection.commit()
            return "Grade added successfully."
        except sqlite3.IntegrityError:
            return "Grade already exists.\nPlease provide unique details."
            
    def close_connection(self):
        self.connection.close()