from util import *
from interface.pages.adminLoginPage import admin_login_page
from interface.pages.studentLoginPage import student_login_page
from interface.pages.teacherLoginPage import teacher_login_page
from interface.pages.registrationPage import registration_page

def welcome_page(root: Tk):
    
    def formard_to_admin_login():
        welcome_page_fr.destroy()
        root.update()
        admin_login_page(root)
        welcome_page(root)
    
    def formard_to_student_login():
        welcome_page_fr.destroy()
        root.update()
        student_login_page(root)
        welcome_page(root)
        
    def formard_to_teacher_login():
        welcome_page_fr.destroy()
        root.update()
        teacher_login_page(root)
        welcome_page(root)
            
    def formard_to_register_page():
        welcome_page_fr.destroy()
        root.update()
        registration_page(root)
        welcome_page(root)
        
    welcome_page_fr = Frame(root, highlightbackground=bg_color, highlightthickness=3)


    heading_lb = Label(welcome_page_fr, text='Login to Continue', bg=bg_color, 
                    fg='white', font=('Bold', 18))
    heading_lb.place(x=0, y=0, width=454)


    admin_login_btn = Button(welcome_page_fr, text='Admin', bg=bg_color, 
                        fg='white', font=('Bold', 15), bd=0,
                            command=formard_to_admin_login)
    admin_login_btn.place(x=140, y=125, width=210)


    student_login_btn = Button(welcome_page_fr, text='Student', bg=bg_color, 
                            fg='white', font=('Bold', 15), bd=0,
                            command=formard_to_student_login)
    student_login_btn.place(x=140, y=185, width=210)

    # student_login_image = Button(welcome_page_fr, image=student_icon, bd=0)
    # student_login_image.place(x=80, y=100)


    teacher_login_btn = Button(welcome_page_fr, text='Teacher', bg=bg_color, 
                            fg='white', font=('Bold', 15), bd=0,
                            command=formard_to_teacher_login)
    teacher_login_btn.place(x=140, y=245, width=210)


    register_login_btn = Button(welcome_page_fr, text='Create Account', bg=bg_color, 
                            fg='white', font=('Bold', 15), bd=0,
                            command=formard_to_register_page)
    register_login_btn.place(x=140, y=305, width=210)
    

    welcome_page_fr.pack(pady=30)
    welcome_page_fr.pack_propagate(False)
    welcome_page_fr.configure(width=460, height=552)