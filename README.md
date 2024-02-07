# tutorial-1

## Reflection 1
Pada tutorial 1 Adpro ini, saya berusaha mengimplementasi prinsip clean code di aplikasi eshop. Saya telah membuat nama-nama variable yang 'meaningful', yakni representatif, singkat, dan padat, contohnya newId merupakan id untuk product yang baru dibuat. Saya juga menggunakan comment hanya untuk meng-emphasize potongan kode saya. Dari segi Secure Coding, kode saya mengimplementasikan testing untuk memenuhi prinsip secure coding. Testing tersebut terdiri dari 2 jenis, yaitu unit testing dan functional testing. Tentunya aspek ini masih dapat ditingkatkan lagi dengan menerapkan authentication serta authorization supaya user hanya dapat melihat product list dirinya sendiri. Dari segi mistake in source code, saya merasa belum mengimplementasi error handling yang optimal. Saya juga merasa bahwa pencarian id yang dilakukan belum efisien.

## Reflection 2

- 1 <br>

Menurut saya, unit test yang perlu ada tergantung dengan konteks method yang digunakan. Namun, idealnya, perlu setidaknya ada positive dan negative scenario untuk satu method unit-test. Untuk poin kedua, menurut saya tidak. Meskipun code coverage telah 100%, bisa saja ada suatu logical error atau corner cases yang tidak dicover test suite kita. <br>

- 2 <br>

Menurut saya, jika terlalu banyak java class yang menggunakan setup prosedur dan instance variables yang sama, akan terlalu repetitif dan bersifat redundant sehingga kurang clean. Jika ingin membuat class lebih banyak dengan setup yang sama, sebaiknya menggunakan java class di file yang sama. Namun jika konteks antara functional tests yang ada memang terasa berbeda, menurut saya bisa saja asal file yang ada tidak terlalu banyak. Seperti yang saya lakukan, yakni membuat functional test CreateProduct (sebuah operasi membuat product) yang berbeda file dengan functional test HomePage (sebatas mengecek title, header, dan semacamnya)
  
  
