from tkinter import *

my_window = Tk()

label_1=Label(my_window,width="20",height="3",bg="red")
label_2=Label(my_window,width="20",height="3",bg="green")
label_3=Label(my_window,width="20",height="3",bg="blue")
label_4=Label(my_window,width="20",height="3",bg="white")
label_5=Label(my_window,width="20",height="3",bg="grey")

label_1.grid(row=0, column=0)
label_2.grid(row=0, column=1)
label_3.grid(row=0, column=2)
label_4.grid(row=1, column=0, columnspan=2, sticky=EW)

status_bar_frame = Frame(my_window,width="250",height="3", bg="black")
status_bar_frame.grid(row=1, column=0, columnspan=3)




my_window.mainloop()
        
          
