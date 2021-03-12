import tkinter as tk
import algorithms as ag
import defaultvalues as dv
import math
import time
from tkinter import messagebox
from tkinter import simpledialog
from tkinter import font
from PIL import Image, ImageTk #please use pip install pillow


class miniProject(tk.Tk):
    selectedStore = "NIL"
    def __init__(self, *args, **kwargs):
        tk.Tk.__init__(self, *args, **kwargs)
        container = tk.Frame(self)
        container.pack(side="top", fill="both", expand = True)
        container.grid_rowconfigure(0, weight=1)
        container.grid_columnconfigure(0, weight=1)

        self.title("Mini Project")
        #self.resizable(False,False)
        self.geometry("900x600")
        self.frames = {}

        for F in (StartPage, StoreSelectionPage, StorePage, dateTimePage):
            frame = F(container, self)
            self.frames[F] = frame
            frame.grid(row=0, column=0, sticky="nsew") #set grid

        self.show_frame(StartPage) #show StartPage

    def getPage(self, pageClass):
        return self.frames[pageClass]

    def show_frame(self, cont): #show frame method to show specified frame
        frame = self.frames[cont]
        frame.tkraise()

class dateTimePage(tk.Frame):
    def __init__(self, parent, controller):
        self.controller = controller
        tk.Frame.__init__(self,parent)

        self.day = tk.StringVar(self)
        self.day.set('01')
        self.mth = tk.StringVar(self)
        self.mth.set('01')
        self.yr = tk.StringVar(self)
        self.yr.set('2019')
        self.hour = tk.StringVar(self)
        self.hour.set('00')
        self.minutes = tk.StringVar(self)
        self.minutes.set('00')

        dayLabel = tk.Label(self, text="Day")      
        monthLabel = tk.Label(self, text="Month")
        yearLabel = tk.Label(self, text="Year")            
        hourLabel = tk.Label(self, text="Hour (24h)")
        minutesLabel = tk.Label(self, text="Minutes")
        
        #Formatting
        
        dd = tk.OptionMenu(self, self.day, *ag.numlist(31))
        mm = tk.OptionMenu(self, self.mth, *ag.numlist(12))
        yyyy = tk.OptionMenu(self, self.yr, "2019", "2020","2021")
        hr = tk.OptionMenu(self, self.hour, *ag.timelist(24))
        mins = tk.OptionMenu(self, self.minutes, *ag.timelist(60))
        # Drop down list to allow user to select specific date
        # dd/mm/yy/tt refers to day/month/year/time, ideal to make month decide the amount of days to display
        backButton = tk.Button(self, text="Back", fg="white", bg="#F44336", borderwidth=4, font=("arial", 12), relief=tk.RAISED, command=lambda: controller.show_frame(StartPage))  # Return to parent window
        confirmButton = tk.Button(self, text="Confirm", fg="white", bg="#F44336", borderwidth=4, font=("arial", 12), relief=tk.RAISED, command=lambda: self.showStoreBasedOnTime())  # This button should use the output from the "day of week function+AM/PM" function, to open another window that displays different store menus


        y = 10

        dayLabel.grid(row=1,column=1, pady=y)
        dd.grid(row=2,column=1, pady=y)
        monthLabel.grid(row=1,column=2, pady=y)
        mm.grid(row=2,column=2, pady=y)
        yearLabel.grid(row=1,column=3, pady=y)
        yyyy.grid(row=2,column=3, pady=y)
        hourLabel.grid(row=3,column=1, pady=y)
        hr.grid(row=4,column=1, pady=y)
        minutesLabel.grid(row=3,column=2, pady=y)
        mins.grid(row=4,column=2, pady=y)
        confirmButton.grid(row=6,column=2, pady=0)
        backButton.grid(row=7, column=2, pady=0)

        self.columnconfigure((1, 3), weight=1)  # add these 2 lines so that content resizes with window!!!!!
        self.rowconfigure((5, 8), weight=1)  ## 1 = fill | 0 = no fill

    def showStoreBasedOnTime(self):

        dtime=ag.usertime(self.day.get(),self.mth.get(),self.yr.get(),self.hour.get(),self.minutes.get())
        #Replaces menu with am version of menu if applicable when the time indicated is before 12 noon.
        now = dtime.tm_hour * 100 + dtime.tm_min
        if now < 1200:
            for F in dv.names:
                ag.timebmenu(dv.amenu, dv.aprices, Allstores[F])
        else:
            for F in dv.names:
                ag.timebmenu(dv.defmenu, dv.defprices, Allstores[F])
        #Generates a list of True/False values corresponding to store indices with True representing open and False representing closed.
        StoreSelectionPageObj = self.controller.getPage(StoreSelectionPage)
        for widget in StoreSelectionPageObj.winfo_children():
            widget.destroy()  # Destroy every widget
        StoreSelectionPageObj.showStoreBasedOnDateTime(dtime)
        self.controller.show_frame(StoreSelectionPage)

class StorePage(tk.Frame):
    def __init__(self, parent, controller):
        self.controller = controller
        tk.Frame.__init__(self, parent)

        self.headerLabel = tk.Label(self, text="null ", fg="black", font=("arial bold", 12))
        self.canvas = tk.Canvas(self, width=250, height=170)
        self.canvas2 = tk.Canvas(self, width=250, height=150)

        self.menuText = tk.Label(self, text="Menu ", fg="black", font=("arial", 26))
        self.pricesText = tk.Label(self, text="Price ", fg="black", font=("arial", 26))
        self.itemsLabel = tk.Label(self, text="null ", fg="black", font=("arial", 20), borderwidth=5, relief="ridge", padx=25, pady=60, anchor='n')
        self.menuPrices = tk.Label(self, text="null ", fg="black", font=("arial", 20), borderwidth=5, relief="ridge", padx=25, pady=60)

        buttonBack = tk.Button(self, text="Back", fg="white", bg="#F44336", borderwidth=4, font=("arial", 12), relief=tk.RAISED, command=lambda: controller.show_frame(StoreSelectionPage))
        opHoursButton = tk.Button(self, text="Operating Hours", fg="white", bg="#F44336", borderwidth=4, font=("arial", 12), relief=tk.RAISED,command=self.op_hours_window)  # Opens the operating hours window
        waitTimeButton = tk.Button(self, text="Wait Time", fg="white", bg="#F44336", borderwidth=4, font=("arial", 12), relief=tk.RAISED, command=self.queue_window)

        f = font.Font(self.menuText, self.menuText.cget("font")) #clones font and set underline attribute to true and assign to label widget
        f.configure(underline=True)
        self.menuText.configure(font=f)
        self.pricesText.configure(font=f)

        self.headerLabel.grid(row=0, column=0, columnspan=3)
        self.canvas.grid(row=1, column=0, columnspan=1, rowspan=2)
        self.canvas2.grid(row=2, column=0, columnspan=1, rowspan=7)

        self.menuText.grid(row=1, column=1, pady=10, sticky='s')
        self.pricesText.grid(row=1, column=2, pady=10, sticky='s')
        self.itemsLabel.grid(row=2, column=1, pady=20, sticky='n')
        self.menuPrices.grid(row=2, column=2, pady=20, sticky='n')

        opHoursButton.grid(row=5, column=0, columnspan=3)
        waitTimeButton.grid(row=6, column=0, columnspan=3)
        buttonBack.grid(row=7, column=0, pady=10, columnspan=3)

        opHoursButton.config(height=1, width=15)
        waitTimeButton.config(height=1, width=15)
        buttonBack.config(height=1, width=15)

        self.columnconfigure((0,2), weight=1) #add these 2 lines so that content resizes with window!!!!!
        self.rowconfigure((3,4), weight=1)
        self.rowconfigure(0, weight=0)

    def queue_window(self):  # Query the user for amount of people in queue, calculates the estimated waiting time, displays it after the user input
        no_of_ppl = tk.simpledialog.askinteger("Waiting Time", "Enter the amount of people in queue")
        time = no_of_ppl * 3
        if time >= 60:
            hh = time//60 #hours
            mm = time - hh*60 #minutes
        a = "Hi, the estimated waiting time is {} hours, {} minutes.".format(hh, mm) #will display hours and minutes
        tk.messagebox.showinfo("Estimated Waiting Time", a)

    def op_hours_window(self):  # Display the operating hours of stores
        return tk.messagebox.showinfo("Operating Hours", ag.ophrsoutput(self.store))

    def setSelectedStore(self, store):
        app.frames[StorePage].store=store
        self.headerLabel['text'] = "Store Selected: " + store.name
        img = store.foodimages1['1']
        img2 = store.foodimages2['2']
        try:
            img = Image.open(img)
            img = img.resize((250, 170), Image.ANTIALIAS)
            photoImg = ImageTk.PhotoImage(img)

            img2 = Image.open(img2)
            img2 = img2.resize((250, 150), Image.ANTIALIAS)
            photoImg2 = ImageTk.PhotoImage(img2)
        except:
            img = Image.open("img/defaultImg.png")
            img = img.resize((240, 120), Image.ANTIALIAS)
            photoImg = ImageTk.PhotoImage(img)
        width = self.canvas.winfo_width() / 2
        height = self.canvas.winfo_height() / 2
        self.canvas.create_image(width, height, anchor=tk.CENTER, image=photoImg)
        self.canvas.image = photoImg

        self.canvas2.create_image(width, height, anchor=tk.CENTER, image=photoImg2)
        self.canvas2.image = photoImg2

        self.itemsLabel['text'] = ag.displaymenu(store)
        self.menuPrices['text'] = ag.displayprice(store)

class StoreSelectionPage(tk.Frame):
    def __init__(self, parent, controller):
        self.controller = controller
        tk.Frame.__init__(self,parent)

    def showStoreBasedOnDateTime(self, time):
        #1. check if weekend or weekday
        #2. check operating hours based on weekend weekday
        dtime = time
        print(dtime)
        operatingStores = ag.checkoperating(dtime, Allstores)
        for i in operatingStores:
            print(i.name)
        #3. put items in list
        headerLabel = tk.Label(self, text="Choose a store", fg="black", font=("arial", 12))
        headerLabel.grid(row=0, column = 1)
        buttonBack = tk.Button(self, text="Back", fg="white",bg="#F44336", font=("arial", 12), relief=tk.RAISED, command=lambda: self.controller.show_frame(StartPage))
        buttonBack.grid(row=(math.ceil(len(operatingStores)/3)+1), column = 1)
        for r in range(math.ceil(len(operatingStores)/3)): #For every 3 items, add new row, r is the specified row index: eg. first row, r = 0
            startIndex = 3 * r
            endIndex = startIndex + 3
            for i in range(startIndex, endIndex):
                try:
                    col = i - (3 * r) #specifies the column index: eg. first column, col = 0
                    img = operatingStores[i].images['header'] #image path retrieved from instance of stores object
                    try:
                        img = Image.open(img) #opens the image object and stores the object
                    except:
                        img = Image.open("img/defaultImg.png") #sets a default image if image couldn't be found, error catching
                    img = img.resize((240, 120), Image.ANTIALIAS) #resizes image to width 240px, height 120px
                    photoImg = ImageTk.PhotoImage(img) #loads image object into external library ImageTk PhotoImage object

                    button = tk.Button(self, text=operatingStores[i].name, image=photoImg, relief=tk.RAISED, command=lambda x=operatingStores[i].name: self.changeToStorePage(x))
                    button.image = photoImg #sets image to button
                    button.grid(row=r+1,column=col, pady=10, padx=25)
                except:
                    continue

        self.columnconfigure(1, weight=1) #add these 2 lines so that content resizes with window!!!!!
        self.rowconfigure(1, weight=0)


    def changeToStorePage(self, StoreName):
        storePageObj = self.controller.getPage(StorePage)
        storePageObj.setSelectedStore(Allstores[StoreName])
        self.controller.show_frame(StorePage)

class StartPage(tk.Frame):

    def __init__(self, parent, controller):
        self.controller = controller
        tk.Frame.__init__(self, parent)
        self.config(bg="white")
        self.timeLabel = tk.Label(self, text="time object",fg="white",bg="grey", font=("arial", 12), )
        self.pixel = tk.PhotoImage(width=1, height=1) #pixel image for button sizes
        background_image = Image.open("img/background.jpg")
        background_image = background_image.resize((640,420), Image.ANTIALIAS)
        self.background =ImageTk.PhotoImage(background_image)

        headerLabel = tk.Label(self, text="Nanyang Technological University \nWelcome to Canteen A Menu System", fg="black", bg="white", font=("arial", 16))
        mainPageImage = tk.Label(self, text="", image = self.background)
        buttonOption1 = tk.Button(self, text="View Today's stores", fg="white", bg="#2196F3",relief=tk.FLAT, font=("arial", 12), width=240, height=50, compound="c", image=self.pixel, command=lambda: self.showStoreSelectionPage())
        buttonOption2 = tk.Button(self, text="View stores by other dates", fg="white",bg="#2196F3", relief=tk.FLAT, font=("arial", 12),width=240, height=50, compound="c", image=self.pixel, command=lambda: controller.show_frame(dateTimePage))
        buttonExit = tk.Button(self, text="Exit", fg="white",bg="#F44336", font=("arial", 12), relief=tk.FLAT,height=50, compound="c", image=self.pixel, command=controller.destroy) #exits program

        self.timeLabel.grid(column=0,row=0, sticky="ew")
        headerLabel.grid(column=0,row=1,sticky="n")
        mainPageImage.grid(column=0,row=2, pady=5)
        buttonOption1.grid(column=0,row=3)
        buttonOption2.grid(column=0,row=4, pady=5)
        buttonExit.grid(column=0,row=5, sticky="ew")
        self.clock_tick()

        self.columnconfigure(0, weight=1)  # add these 2 lines so that content resizes with window!!!!!
        self.rowconfigure(2, weight=1)  ##

    def showStoreSelectionPage(self):
        StoreSelectionPageObj = self.controller.getPage(StoreSelectionPage)
        for widget in StoreSelectionPageObj.winfo_children():
            widget.destroy() #Destroy every widget
        dtime = ag.currenttime()
        # Replaces menu with am version of menu if applicable when the time indicated is before 12 noon.
        now = dtime.tm_hour * 100 + dtime.tm_min
        if now < 1200:
            for F in dv.names:
                ag.timebmenu(dv.amenu, dv.aprices, Allstores[F])
        else:
            for F in dv.names:
                ag.timebmenu(dv.defmenu, dv.defprices, Allstores[F])

        StoreSelectionPageObj.showStoreBasedOnDateTime(dtime)
        self.controller.show_frame(StoreSelectionPage)

    def clock_tick(self):
        clock = ''
        new_clock = time.strftime('%H:%M:%S') #24 Hour time ticker
        if new_clock != clock:
            clock = new_clock
            self.timeLabel.config(text="Time now: "+new_clock)
            self.timeLabel.after(200, self.clock_tick)


StoreCount=len(dv.names)
Allstores={}
for index in range(StoreCount):
    Allstores[dv.names[index]]=dv.stores(index,dv.names,dv.defmenu,dv.defprices,dv.ophours)


app = miniProject()
app.mainloop()