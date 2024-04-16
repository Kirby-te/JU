# from util import *
# from college import SubjectDB
# from student import StudentDB, MarkDB, ResultDB

# bg_color_option='#c3c3c3'

# def display_result_page(root: Tk):
        
            
#     dashboard_fr = Frame(root, highlightbackground=bg_color, highlightthickness=3)
    
    
#     pages_fr = Frame(dashboard_fr)
#     pages_fr.place(x=122, y=5, width=350, height=550)
        
        
#     def get_student_details():
#         stud = StudentDB()
#         markD = MarkDB()
#         subject_id = user[6]
#         identifier = search_input.get()
        
#         if find_by_option_btn.get() == 'roll':
#             details = stud.get_details_by_roll(identifier)
#         elif find_by_option_btn.get() == 'name':
#             details = stud.get_details_by_name(identifier)
        
#         # clear privious results
#         for item in record_table.get_children():
#             record_table.delete(item)
            
#         if details:                    
#             for detail in details:
#                 mark = markD.get_mark(detail[0], subject_id)
#                 if not mark:
#                     mark = '-'
#                 student_detail = [detail[0], detail[2] + " " + detail[3], mark]
#                 record_table.insert(parent='', index='end', values=student_detail)
        
#         stud.close_connection()
#         markD.close_connection()

#     def generate_student_card():
#         selection = record_table.selection()
#         selected_roll = record_table.item(item=selection, option='values')[0]
        
#         stud = StudentDB()
#         student_details = stud.get_details(selected_roll)
        
#         student_card_fr = Frame(find_student_page_fr, highlightbackground='black', highlightthickness=3)
        
#         heading_lb = Label(student_card_fr, text='Student Info', bg=bg_color, 
#                         fg='white', font=('Bold', 13))
#         heading_lb.place(x=0, y=0, width=344)
        
#         back_btn = Button(student_card_fr, text='back', bg=bg_color,
#                         fg='white', font=('Bold', 15), command=student_card_fr.destroy)
#         back_btn.place(x=10, y=40)

#         details_lb = Label(student_card_fr,
#                         text='''Email : {}\n\nRoll No. : {}\n\nName : {}\n\nPhone No. : {}'''.format(student_details[1], student_details[0], student_details[3] + " " + student_details[4], student_details[5]),
#                         font=('Bold', 13), justify=LEFT)
#         details_lb.place(x=20, y=130)
        
#         student_card_fr.pack(pady=10)
#         student_card_fr.pack_propagate(False)
#         student_card_fr.configure(width=400, height=400)
#         stud.close_connection()
    
#     def edit_mark():
#         if not user[6]:
#             message_box(root, "You are not \nassociated \nwith any subject")
#             return
        
#         stud = StudentDB()
#         markD = MarkDB()

        
#         selection = record_table.selection()
#         selected_roll = record_table.item(item=selection, option='values')[0]
        
#         student_details = stud.get_details(selected_roll)
#         current_mark = markD.get_mark(student_details[0], s[6])
#         if not current_mark:
#             current_mark = '-'
        
#         student_card_fr = Frame(find_student_page_fr, highlightbackground='black', highlightthickness=3)
        
#         heading_lb = Label(student_card_fr, text='Update Marks', bg=bg_color, 
#                         fg='white', font=('Bold', 13))
#         heading_lb.place(x=0, y=0, width=344)
        
#         back_btn = Button(student_card_fr, text='back', bg=bg_color,
#                         fg='white', font=('Bold', 10), command=student_card_fr.destroy)
#         back_btn.place(x=10, y=40)

#         details_lb = Label(student_card_fr,
#                         text='''Roll No. : {}\nCurrent Score : {}'''.format(student_details[0], current_mark),
#                         font=('Bold', 13), justify=LEFT)
#         details_lb.place(x=20, y=100)
        
#         new_mark_lb = Label(student_card_fr, text='Enter New Score', font=('Bold', 13))
#         new_mark_lb.place(x=20, y=180)
        
#         new_mark_input = Entry(student_card_fr, font=('Bold', 13))
#         new_mark_input.place(x=20, y=210)
        
#         submit_btn = Button(student_card_fr, text='Submit', font=('Bold', 15),
#                     bg=bg_color, fg='white', command=update_marks)
#         submit_btn.place(x=120, y=260, height=40)
        
#         student_card_fr.pack(pady=10)
#         student_card_fr.pack_propagate(False)
#         student_card_fr.configure(width=400, height=350)
#         stud.close_connection()

#     def enable_buttons():
#         generate_student_card_btn.config(state=NORMAL)
#         edit_mark_btn.config(state=NORMAL)

    
#     search_filters = ['roll', 'name']
    
#     find_student_page_fr = Frame(pages_fr)
    
#     find_student_record_lb = Label(find_student_page_fr, text='Update Student Score', font=('Bold', 13), fg="white", bg=bg_color)
#     find_student_record_lb.place(x=20, y=10, width=300)
    
#     find_by_lb = Label(find_student_page_fr, text="Find By:", font=('Bold', 12))
#     find_by_lb.place(x=15, y=50)
    
#     find_by_option_btn = Combobox(find_student_page_fr, font=('Bold', 12), state='readonly', values=search_filters)
#     find_by_option_btn.place(x=80, y=50, width=80)
#     find_by_option_btn.set('roll')
    
#     search_input = Entry(find_student_page_fr, font=('Bold', 12))
#     search_input.place(x=20, y=90)
#     search_input.bind('<KeyRelease>', lambda e: get_student_details())
    
#     record_table_lb = Label(find_student_page_fr, text='Record Table', font=('Bold', 13), bg=bg_color, fg='white')
#     record_table_lb.place(x=20, y=160, width=300)
    
#     record_table = Treeview(find_student_page_fr)
#     record_table.place(x=0, y=200, width=350)
#     record_table.bind('<<TreeviewSelect>>', lambda e: enable_buttons())
    
#     record_table['columns'] = ('roll', 'name', 'marks')
#     record_table.column('#0', stretch=NO, width=0)
    
#     record_table.heading('roll', text='Roll No', anchor=W)
#     record_table.column('roll', width=35, anchor=W)
#     record_table.heading('name', text='Name', anchor=W)
#     record_table.column('name', width=85, anchor=W)
#     record_table.heading('marks', text='{subject_name} Marks', anchor=W)
#     record_table.column('marks', width=45, anchor=W)
    
#     generate_student_card_btn = Button(find_student_page_fr, text='Show Student Card', font=('Bold', 13), bg=bg_color, fg='white', state=DISABLED, command=generate_student_card) 
#     generate_student_card_btn.place(x=160, y=450)
    
#     edit_mark_btn = Button(find_student_page_fr, text='Edit Mark', font=('Bold', 13), bg=bg_color, fg='white', state=DISABLED, command=edit_mark)
#     edit_mark_btn.place(x=10, y=450)
    
    
#     find_student_page_fr.pack(fill=BOTH, expand=True)
        
    
    
    
#     dashboard_fr.pack(pady=5)
#     dashboard_fr.pack_propagate(False)
#     dashboard_fr.configure(width=480, height=580)
    