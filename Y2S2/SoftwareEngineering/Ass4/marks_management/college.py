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
        