import sys
sys.path.append('../marks_management')

from teacher import TeacherDB
 
teacher_db = TeacherDB()

# teacher_db.drop_table()

teacher_details = [
    ("john@example.com", "1234", "John", "Doe", "1234567890", 1),
    ("jane@example.com", "1234", "Jane", "Smith", "9876543210", 2),
    ("alice@example.com", "1234", "Alice", "Johnson", "5551234567", 3),
    ("bob@example.com", "1234", "Bob", "Williams", "9871234567", 4),
    ("charlie@example.com", "1234", "Charlie", "Brown", "1239876543", 5),
    ("emma@example.com", "1234", "Emma", "Garcia", "9876541230", 6),
    ("david@example.com", "1234", "David", "Martinez", "4567890123", None)
]

for details in teacher_details:
    password, first_name, last_name, email, phoneNo, subject_id = details
    teacher_db.add_teacher(password, first_name, last_name, email, phoneNo, subject_id)

# teacher_db.update_teacher(1, None, None, None, None, None, 1)

teacher_db.close_connection()
