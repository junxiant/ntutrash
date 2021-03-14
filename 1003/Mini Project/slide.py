from PIL import Image, ImageTk
import io
from itertools import cycle
import tkinter as tk

class App(tk.Tk):
    def __init__(self, image_files, delay):

        tk.Tk.__init__(self)

        self.delay = delay
        # allows repeat cycling through the pictures

        self.pictures = cycle((self.photo_image(image), image)
                              for image in image_files)
        self.picture_display = tk.Label(self)
        self.picture_display.pack()

    def show_slides(self):
        imgobj, img = next(self.pictures)
        self.picture_display.config(image=imgobj)
        self.after(self.delay, self.show_slides)

    def photo_image(self, pics):
        with io.open(pics, 'rb') as pic:
            image = Image.open(pic)
            return ImageTk.PhotoImage(image)

    def run(self):
        self.mainloop()

delay = 2000

image_files = [
'1.png',
'2.png'
]

app = App(image_files, delay)
app.show_slides()
app.run()