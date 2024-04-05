import sqlite3

class AdminDB:
    def __init__(self, db_path = './database/college.db'):
        self.db_path = db_path
        self.connection = sqlite3.connect(self.db_path)
        self.cursor = self.connection.cursor()
        self.create_table()
    
    def create_table(self):
        self.cursor.execute("""CREATE TABLE IF NOT EXISTS
            admins
            (
                id INTEGER PRIMARY KEY,
                email TEXT UNIQUE,
                password TEXT NOT NULL
            )
            """)
        self.connection.commit()
    
    def add_admin(self, email: str, password: str) -> str:
        if (not email) or (not password):
            return "One or more fields, entered are blanks."
        try:
            self.cursor.execute("INSERT INTO admins (email, password) VALUES (?, ?)",
                                (email, password))
            self.connection.commit()
            return "Admin added successfully.\nLogin with Email."
        except sqlite3.IntegrityError:
            return "Email already exists.\nPlease provide unique details."

    def update_admin(self, id: int, new_email: str) -> str:
        try:
            self.cursor.execute("UPDATE admins SET email=? WHERE id=?",
                                (new_email, id))
            self.connection.commit()
            return "admin updated successfully."
        except sqlite3.Error as e:
            print("Error occurred:", e)

    def remove_admin(self, id: int) -> str:
        try:
            self.cursor.execute("DELETE FROM admins WHERE id=?", (id,))
            self.connection.commit()
            return "Admin removed successfully."
        except sqlite3.Error as e:
            print("Error occurred:", e)
            
    def email_exists(self, email: str) -> bool:
        self.cursor.execute("SELECT * FROM admins WHERE email=?", (email,))
        if self.cursor.fetchone():
            return True
        else:
            return False

    def id_exists(self, id: int) -> bool:
        self.cursor.execute("SELECT * FROM admins WHERE id=?", (id,))
        if self.cursor.fetchone():
            return True
        else:
            return False
        
    def get_password(self, identifier: str) -> str:
        query = "SELECT password FROM admins WHERE email = ? OR id = ?"
        self.cursor.execute(query, (identifier, identifier))
        result = self.cursor.fetchone()
        if result:
            return result[0]
        else:
            return None

    def close_connection(self):
        self.connection.close()

    
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
    
    def subject_exists(self, subject_id: int) -> bool:
        self.cursor.execute("SELECT * FROM subjects WHERE subject_id=?", (subject_id,))
        if self.cursor.fetchone():
            return True
        else:
            return False
    
    def get_subject_name(self, subject_id: int) -> str:
        self.cursor.execute("SELECT subject_name FROM subjects WHERE subject_id=?", (subject_id,))
        sub  = self.cursor.fetchone()
        if sub:
            return sub[0]
        else:
            return None
        
    def get_subjec_id(self, subject_name: str) -> int:
        self.cursor.execute("SELECT subject_id FROM subjects WHERE subject_name=?", (subject_name,))
        sub  = self.cursor.fetchone()
        if sub:
            return sub[0]
        else:
            return None
        
    def get_subject_names(self) -> list:
        self.cursor.execute("SELECT subject_name FROM subjects")
        subjects = self.cursor.fetchall()
        return [sub[0] for sub in subjects]
        
    def drop_table(self):
        try:
            self.cursor.execute("DROP TABLE IF EXISTS subjects")
            self.connection.commit()
            print("Table 'marks' dropped successfully.")
        except sqlite3.Error as e:
            print("Error occurred while dropping table 'subjects':", e)
        
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
        
    def drop_table(self):
        try:
            self.cursor.execute("DROP TABLE IF EXISTS grades")
            self.connection.commit()
            print("Table 'marks' dropped successfully.")
        except sqlite3.Error as e:
            print("Error occurred while dropping table 'grades':", e)

    def close_connection(self):
        self.connection.close()