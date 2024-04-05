import sys
sys.path.append('../marks_management')

from teacher import TeacherDB
 
teacher_db = TeacherDB()

# teacher_db.drop_table()
teacher_details = [
    ("john@example.com", "1234", "John", "Doe", "1234567890"),
    ("jane@example.com", "1234", "Jane", "Smith", "9876543210"),
    ("alice@example.com", "1234", "Alice", "Johnson", "5551234567"),
    ("bob@example.com", "1234", "Bob", "Williams", "9871234567"),
    ("charlie@example.com", "1234", "Charlie", "Brown", "1239876543"),
    ("emma@example.com", "1234", "Emma", "Garcia", "9876541230"),
    ("david@example.com", "1234", "David", "Martinez", "4567890123")
]

for details in teacher_details:
    password, first_name, last_name, email, phoneNo = details
    teacher_db.add_teacher(password, first_name, last_name, email, phoneNo)

teacher_db.update_teacher(1, None, None, None, None, None, 1)

teacher_db.close_connection()
