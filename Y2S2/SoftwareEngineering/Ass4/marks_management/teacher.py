import sqlite3

class TeacherDB:    
    def __init__(self, db_path = './database/teachers.db'):
        self.db_path = db_path
        self.connection = sqlite3.connect(self.db_path)
        self.cursor = self.connection.cursor()
        self.create_table()
        
    def create_table(self):
        self.cursor.execute("""CREATE TABLE IF NOT EXISTS
            teachers
            (
                id TEXT INTEGER PRIMARY KEY,
                password TEXT NOT NULL,
                first_name TEXT,
                last_name TEXT,
                email TEXT UNIQUE,
                phoneNo TEXT
            )
            """)
        self.connection.commit()
    
    def add_teacher(self, password, first_name, last_name, email, phoneNo):
        try:
            self.cursor.execute("INSERT INTO teachers (password, first_name, last_name, email, phoneNo) VALUES (?, ?, ?, ?, ?)",
                                (password, first_name, last_name, email, phoneNo))
            self.connection.commit()
            print("Teacher added successfully.")
        except sqlite3.IntegrityError:
            print("Email already exists. Please provide unique details.")

    def update_teacher(self, id, new_email, new_phoneNo):
        try:
            self.cursor.execute("UPDATE teachers SET email=?, phoneNo=? WHERE id=?",
                                (new_email, new_phoneNo, id))
            self.connection.commit()
            print("Teacher updated successfully.")
        except sqlite3.Error as e:
            print("Error occurred:", e)

    def remove_teacher(self, id):
        try:
            self.cursor.execute("DELETE FROM teachers WHERE id=?", (id))
            self.connection.commit()
            print("Teacher removed successfully.")
        except sqlite3.Error as e:
            print("Error occurred:", e)

    def close_connection(self):
        self.connection.close()