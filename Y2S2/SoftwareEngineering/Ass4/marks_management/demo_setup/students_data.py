import sys
sys.path.append("../marks_management")

from student import StudentDB, MarkDB, ResultDB
import random

# student_db = StudentDB()

# student_details = [
#     ("student1@example.com", "password1", "John", "Doe", "1234567890"),
#     ("student2@example.com", "password2", "Jane", "Smith", "9876543210"),
#     ("student3@example.com", "password3", "Alice", "Johnson", "5551234567"),
#     ("student4@example.com", "password4", "Bob", "Williams", "9871234567"),
#     ("student5@example.com", "password5", "Charlie", "Brown", "1239876543"),
#     ("student6@example.com", "password6", "Emma", "Garcia", "9876541230"),
#     ("student7@example.com", "password7", "David", "Martinez", "4567890123"),
#     ("student8@example.com", "password8", "Michael", "Taylor", "9876543210"),
#     ("student9@example.com", "password9", "Sophia", "Anderson", "1234567890"),
#     ("student10@example.com", "password10", "Olivia", "Thomas", "5551234567"),
#     ("student11@example.com", "password11", "James", "Hernandez", "9871234567"),
#     ("student12@example.com", "password12", "William", "Young", "1239876543"),
#     ("student13@example.com", "password13", "Daniel", "King", "9876541230"),
#     ("student14@example.com", "password14", "Emily", "Wright", "4567890123"),
#     ("student15@example.com", "password15", "Sarah", "Lopez", "9876543210"),
#     ("student16@example.com", "password16", "Ashley", "Scott", "1234567890"),
#     ("student17@example.com", "password17", "Madison", "Green", "5551234567"),
#     ("student18@example.com", "password18", "Joseph", "Adams", "9871234567"),
#     ("student19@example.com", "password19", "David", "Baker", "1239876543"),
#     ("student20@example.com", "password20", "Robert", "Collins", "9876541230")
# ]

# for details in student_details:
#     email, password, first_name, last_name, phoneNo = details
#     student_db.add_student(email, password, first_name, last_name, phoneNo)

# student_db.close_connection()





# marks = MarkDB()
# stud = StudentDB()
# rolls = stud.get_rolls()
# # marks.add_mark(1, 1, 25)
# # marks.remove_grade(1)

# for roll in rolls:
#     for subject_id in range(1, 7):
#         mark = random.randint(35, 95)
#         marks.add_mark(roll[0], subject_id, mark)

# stud.close_connection()
# marks.close_connection()




res = ResultDB()
# res.remove_result(1)
# res.add_result(1, ['S', 'S', 'A', 'F', 'E', 'C'])

stud = StudentDB()
rolls = stud.get_rolls()
# print(rolls)
marks = MarkDB()

for roll in rolls:
    grades = []
    for i in range (1, 7):
        grade = marks.get_grade(roll[0], i)
        grades.append(grade)
    # print(roll[0], grades)
    res.add_result(roll[0], grades)
        
marks.close_connection()
stud.close_connection()
res.close_connection()