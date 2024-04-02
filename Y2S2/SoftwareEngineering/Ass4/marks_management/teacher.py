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
                id INTEGER PRIMARY KEY,
                email TEXT UNIQUE,
                password TEXT NOT NULL,
                first_name TEXT,
                last_name TEXT,
                phoneNo TEXT
            )
            """)
        self.connection.commit()
    
    def add_teacher(self, email: str, password: str, first_name: str, last_name: str, phoneNo: str):
        try:
            self.cursor.execute("INSERT INTO teachers (email, password, first_name, last_name, phoneNo) VALUES (?, ?, ?, ?, ?)",
                                (email, password, first_name, last_name, phoneNo))
            self.connection.commit()
            print("Teacher added successfully.")
        except sqlite3.IntegrityError:
            print("Email already exists. Please provide unique details.")

    def update_teacher(self, id: int, new_email: str, new_phoneNo: str):
        try:
            self.cursor.execute("UPDATE teachers SET email=?, phoneNo=? WHERE id=?",
                                (new_email, new_phoneNo, id))
            self.connection.commit()
            print("Teacher updated successfully.")
        except sqlite3.Error as e:
            print("Error occurred:", e)

    def remove_teacher(self, id: int):
        try:
            self.cursor.execute("DELETE FROM teachers WHERE id=?", (id,))
            self.connection.commit()
            print("Teacher removed successfully.")
        except sqlite3.Error as e:
            print("Error occurred:", e)

    def close_connection(self):
        self.connection.close()