## Website Link: https://eshop-farrelayman.koyeb.app/

# tutorial-1

## Reflection 1
Pada tutorial 1 Adpro ini, saya berusaha mengimplementasi prinsip clean code di aplikasi eshop. Saya telah membuat nama-nama variable yang 'meaningful', yakni representatif, singkat, dan padat, contohnya newId merupakan id untuk product yang baru dibuat. Saya juga menggunakan comment hanya untuk meng-emphasize potongan kode saya. Dari segi Secure Coding, kode saya mengimplementasikan testing untuk memenuhi prinsip secure coding. Testing tersebut terdiri dari 2 jenis, yaitu unit testing dan functional testing. Tentunya aspek ini masih dapat ditingkatkan lagi dengan menerapkan authentication serta authorization supaya user hanya dapat melihat product list dirinya sendiri. Dari segi mistake in source code, saya merasa belum mengimplementasi error handling yang optimal. Saya juga merasa bahwa pencarian id yang dilakukan belum efisien.

## Reflection 2

- 1 <br>

Menurut saya, unit test yang perlu ada tergantung dengan konteks method yang digunakan. Namun, idealnya, perlu setidaknya ada positive dan negative scenario untuk satu method unit-test. Untuk poin kedua, menurut saya tidak. Meskipun code coverage telah 100%, bisa saja ada suatu logical error atau corner cases yang tidak dicover test suite kita. <br>

- 2 <br>

Menurut saya, jika terlalu banyak java class yang menggunakan setup prosedur dan instance variables yang sama, akan terlalu repetitif dan bersifat redundant sehingga kurang clean. Jika ingin membuat class lebih banyak dengan setup yang sama, sebaiknya menggunakan java class di file yang sama. Namun jika konteks antara functional tests yang ada memang terasa berbeda, menurut saya bisa saja asal file yang ada tidak terlalu banyak. Seperti yang saya lakukan, yakni membuat functional test CreateProduct (sebuah operasi membuat product) yang berbeda file dengan functional test HomePage (sebatas mengecek title, header, dan semacamnya)
  
# tutorial-2

## Reflection
- 1 List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them. <br>
  Dari segi quality issues, saya memakai PMD untuk code scanning. Dari aspek code itu sendiri, saya sudah menambah beberapa unit test supaya dapat meng-increase code coverage percentage.


- 2 Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)! <br>
Iya, saya merasa sudah memenuhi CI workflow, tetapi CD masih belum. Code saya sudah terintegrasi dan mengautomsi proses CI workflow. Setiap saya push atau melakukan pull request. akan ter-trigge. Setelah itu unit test akan langsung mengecek kode saya. Saya juga menggunakan Scorecard and PMD workflow untuk mengecek security dan quality dari code saya.

# tutorial-3

## Reflection
1) Explain what principles you apply to your project! <br>
- Saya mengimplementasi sendiri Single Responsibility Principle (SRP) di aplikasi ini. SRP merupakan prinsip di mana aplikasi yang kita buat harus modular di mana suatu class sebaiknya punya singular responsibility. Kita dapat melakukannya dengan mengorganisasikan aplikasi kita menjadi layers dan men-decompose large classes kita menjadi class yang lebih modular. Saya sendiri mengimplementasi prinsip ini dengan mengubah CarController yang awalnya package-private dan berada di file yang sama dengan productController, menjadi public dan pindah ke file yang berbeda dari ProductController. <br>
- Open-Closed Principle sudah terimplementasi di aplikasi dari awal karena tidak ada super class yang di-alter. Prinsip ini mengatakan bahwa sebuah "software artifact should be open for extension, but not for modification". Contoh implementasinya di module ini adalah CarController yang meng-extend ProductController dan tidak memodifikasi superclass (ProductController) sama sekali. Implementasi prinsip ini dapat membantu mencegah adanya bug baru yang bisa terjadi ketika kita malah membuat perubahan yang berhubungan dengan parent class yang telah ada.
- Liskov-Substitution Principle sudah terimplementasi di aplikasi dari awal. LSP merupakan prinsip di mana suatu subclass di-expect dapat melakukan suatu behaviour sebagaimana dapat dilakukan oleh parent class. Implementasinya di aplikasi ini adalah class CarController yang menginherit ProductController dan bisa melakukan hal-hal yang dilakukan oleh parent class-nya, seperti create, list, edit, dan delete. Perbedaannya terdapat dalam konteks pembuatan objek/model di mana ProductController berurusan dengan Product, sementara CarController berurusan dengan yang lebih spesifik, yakni Car.
- Interface Segregation Principle sudah terimplementasi di aplikasi dari awal. ISP merupakan prinsip yang menggarisbawahi pemisahan interface sehingga lebih modular dan kontekstual. Hal ini memungkinkan class untuk mengimplmentasi interface hanya yang dibutuhkan olehnya saja. Dengan prinsip ini, class akan hanya mengimplementasi interface yang relevan dan focused dengan mereka. Implementasinya di aplikasi ini adalah interface CarService yang sudah bersifat modular dan kontekstual. Oleh karena itu, Ketika CarServiceImpl mengimplementasi interface CarService tersebut, method-method yang digunakan pun masih bersifat relevan dengan CarServiceImpl, seperti create, findAll, findById, update, dan deleteById.
- Saya mengimplementasi sendiri Dependency Inversions Principle (DIP) di aplikasi ini. DIP sendiri merupakan prinsip di mana kita diimbau untuk menggunakan abstrasi dari suatu class, bukan concrete class itu sendiri. Saya mengimplentasinya dengan mengubah tulisan CarServiceImpl di class CarController menjadi CarService. Dengan melakukan ini, dipastikan bahwa programmer lebih fleksibel dan tidak terbebani detail spesifik yang biasa terdapat di implementasi-implementasi berbeda.


2) Explain the advantages of applying SOLID principles to your project with examples.
- Lebih modular dan maintainable
- Minimize dependencies
- Mudah menambkan fungsionalitas baru
- Lebih mudah melakukan testing
- Contoh: Dengan memisahkan CarController dan ProductController ke file yang berbeda, aplikasi akan lebih modular. Hal ini berarti aplikasi lebih Lower coupling sehingga men-decrease dependencies. Selain itu, karena organization yang smaller dan well-structured, class akan lebih mudah dinavigasi daripada class yang large dan monolithic.
- Contoh2: Dengan menggunakan CarService alih-alih CarServiceImpl di CarController (ISP), developer akan lebih fleksibel dan tidak perlu mengkhawatirkan mengenai implementasi spesifik yang dimiliki oleh suatu concrete class.

3) Explain the disadvantages of not applying SOLID principles to your project with examples.
- Lebih sulit dibaca
- Lebih rumit dalam menambahkan fungsionalitas baru
- Lebih rawan mendapatkan bug yang kian bertambah.
- Contoh: Dengan menerapkan Open-closed principle, seperti hubungan CarController dengan ProductController, kita bisa menambahkan fungsionalitas baru tanpa memodifikasi suatu parent class yang sudah ada. Tanpa menggunakan prinsip ini, bug baru rawan muncul karena kita telah mengotak-atik functioning parent class tersebut.
- Contoh2: Jika kita tidak menerapkan prinsip ISP di aplikasi kita, Ke depannya, ketika implementasi kita lebih banyak dan variatif, hal ini akan rumit karena membuat developer khawatir akan implementasi spesifik dari concrete classes yang ada. Hal ini juga akan sangat terasa ketika kita ingin mengganti suatu objek concrete class1 dengan objeck concrete class2 di mana bisa saja implementasi method-methodnya sangat berbeda

# tutorial-4

## Reflection 1

1) Menurut saya, TDD Flow ini lumayan berguna, tetapi bisa dikatakan sulit dibanding test pada biasanya. Hal ini karena kita memulai dengan membuat test sehingga bagi saya bayangan yang didapatkan lebih abstrak dibanding biasanya. Ke depannya saya akan memerhatikan kebutuhan testcases seperti jenis voucher apa yang diterima dan lain-lain. Dengan memahami lebih dalam hal tsb, saya merasa dapat lebih membayangkan test-test yang dibutuhkan

2) Saya merasa bahwa test yang saya buat sudah mengikuti prinsip FIRST. Hal ini karena test-test sudah cepat, konsisten, repeatable, dan juga sudah mencakup positive and negative test cases.


