from util import *
from interface.pages.welcomePage import welcome_page
from interface.dashboard.studentDashoard import student_dashboard
from interface.dashboard.teacherDashboard import teacher_dashboard
from interface.dashboard.adminDashboard import admin_dashboard
from interface.pages.displayResultPage import display_result_page

root = Tk()
root.title("Marks Management System")
# root.iconbitmap(logo_icon)
root.geometry("500x600")

# loading images
# logo_icon = PhotoImage(file='assets/logo.png')
# student_icon = PhotoImage(file='assets/student_icon.png')
# teacher_icon = PhotoImage(file='assets/teacher_icon.png')
# admin_icon = PhotoImage(file='assets/admin_icon.png')
# register_icon = PhotoImage(file='assets/register_icon.png')

# welcome_page(root)
# student_dashboard(root, 2)
# teacher_dashboard(root, 7)
# admin_dashboard(root, 1)
display_result_page(root)

root.mainloop()