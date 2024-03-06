Nama: Lucinda Laurent<br>
NPM: 2206024745<br>
Kelas: Adpro B<br>

# Tutorial 1

<details>
<summary>Reflection 1</summary>

### Reflection 1
_Clean code principles_ yang telah saya coba terapkan pada kode untuk fitur `edit` dan `delete` saya antara lain:
* Meaningful names<br>
Saya sudah memberi nama variabel, fungsi, kelas, maupun argumen yang menggambarkan apa yang sedang dikerjakan.
Misalnya ketika testing, saya memberi nama variabel produk yang tidak terhapus dari list sebagai `remainingProduct`.
* Function<br>
Saya sudah berusaha membuat fungsi (method) yang hanya melakukan satu hal saja.
Misalnya untuk fitur edit dan delete product, saya membuat helper method untuk mencari produk yang datanya akan diupdate. 
Dengan begitu, method edit dan delete saya benar-benar hanya mengupdate atau menghapus produk dari list.
* Comments<br>
Dengan nama variabel dan fungsi yang representatif, serta alur program yang sederhana saya tidak perlu memberi komentar pada kode saya.
* Objects and Data Structure<br>
Misalnya pada kelas `Product`, atribut-atributnya dibuat private sehingga bersifat "independen" terhadap kode di luar kelas tersebut.
* Error handling<br>
Saya sudah berusaha melakukan error handling dengan menggunakan custom exception untuk memberi pesan error yang spesifik.

_Secure coding practice_ yang telah saya coba terapkan pada kode untuk fitur `edit` dan `delete` hanya `input data validation` dari sisi klien.
Saya menggunakan atribut `required` pada tag input HTML untuk nama produk dan atribut `type=number` dan `min="0"` untuk kuantitas produk.<br><br>
Selain clean code principles dan secure coding practice, saya sudah menerapkan feature branch workflow serta membuat unit test dan menerapkan functional test sederhana.
#### Kekurangan kode
* Belum menerapkan beberapa secure coding practice seperti authentication dan authorization. 
* Belum menerapkan input data validation dari sisi server.
* Masih terdapat redundansi pada kode unit test saat membuat dummy data. 
</details>

<details>
<summary>Reflection 2</summary>

### Reflection 2
1. Setelah menulis unit test, saya merasa senang karena sudah menyelesaikan tugasnya :) Saya juga merasa lebih yakin dengan kebenaran kode yang telah saya buat karena selain testing manual, kode saya juga lulus semua unit test yang saya buat sendiri.<br>
Jumlah unit test yang harus dibuat sebaiknya menyesuaikan jumlah fungsionalitas program yang dibuat. Fungsionalitas tersebut salah satunya bisa dilihat dari jumlah method yang ada, mulai dari constructor, getter, setter, maupun fitur-fitur seperti create, delete, edit, dan lain-lain.<br>
Untuk memastikan unit test yang dibuat sudah cukup untuk memverifikasi program kita, kita perlu mencoba menghandle semua kemungkinan interaksi yang bisa dilakukan pengguna dengan program kita. Penggunaan konsep code coverage dapat membantu kita untuk memastikan kita sudah melakukan tes pada keseluruhan program.<br>
Di sisi lain, 100% code coverage tidak menjamin program kita bebas dari bug dan error. Code coverage hanya menggambarkan berapa persen source code yang telah dites, sehingga dapat membantu kita mengecek apakah ada bagian program yang belum dites. Sayangnya code coverage tidak memberi tahu kita apakah tes yang kita buat sudah cukup baik. Bisa saja program kita sudah lulus unit tes yang kita buat, namun masih terdapat logic error pada alur program atau ada skenario yang tidak terpikirkan. <br>
2. Jika kita membuat kelas _functional test_ baru yang serupa dengan kelas sebelumnya, kode tersebut menurut saya akan jadi kurang bersih karena bersifat _redundant_. 2 kode yang serupa tentu kurang efisien dan cenderung sulit di-maintain karena jika kita ingin mengubah setupnya, kita perlu mengubahnya 2 kali. Untuk membuat kodenya lebih bersih mungkin functional test baru tersebut bisa ditambahkan di kelas `CreateProductFunctionalTest` yang sudah ada. Kita bisa membuat fungsi khusus untuk instansiasi produk, sehingga fungsi testingnya bisa fokus untuk mengecek kebenaran output saja.
</details>

# Tutorial 2
Take notes:
1. Code coverage modul 1 secara keseluruhan di angka 53%. Setelah menambahkan unit test untuk ProductServiceImpl, code coverage meningkat ke 71%.
2. Detected code quality issues dari PMD antara lain:
* Node.js 16 actions are deprecated.
* Consider make classes that only have static methods as utility classes. 
* Unused import 'org.springframework.web.bind.annotation.*'
* Unnecessary modifier 'public' on method 'delete': the method is declared in an interface type
3. Link deployment : https://advprog-eshop-lucinda-laurent.koyeb.app/
<details>
<summary>Reflection</summary>

1. Code quality issue(s) dari PMD code analysis yang saya perbaiki selama latihan:
* Penggunaan modifier 'public' untuk method di dalam interface 'ProductService'
Method di dalam suatu interface sudah 'public abstract' by default sehingga kita tidak perlu menuliskan modifier public secara eksplisit.
Solusi: menghapus modifier 'public' tersebut dari method-method di interface
* Import statement yang tidak terpakai
Terkadang ada import yang lupa dihapus ketika apa yang diimpor tidak jadi digunakan di dalam program. 
Solusi: menghapus import statement yang tidak jadi digunakan <br>

2. Menurut saya, implementasi CI/CD workflows saya sudah memenuhi definisi Continuous Integration and Continuous Deployment: <br>
   Continuous Integration merupakan praktik untuk mengautomasi proses integrasi dan verifikasi setiap perubahan pada kode dengan bantuan alat. Dengan menggunakan script untuk menjalankan unit test (`ci.yml`), menganalisis isu keamanan (`scorecard.yml`), serta identifikasi potensi masalah dalam program (`pmd.yml`) setiap melakukan push(juga merge) ke repository Github, kode saya sudah menerapkan automasi proses integrasi dan verifikasi. <br> 
Continuous Deployment merupakan praktik untuk mengautomasi proses deployment aplikasi ke server tertentu. Dengan mengintegrasikan layanan `Koyeb` ke repositori Github, aplikasi eshop akan ter-deploy secara otomatis setiap kali saya melakukan push ke branch main di repositori Github.
</details>

# Tutorial 3
<details>
<summary>Reflection</summary>
1. SOLID Principles yang telah saya aplikasikan yaitu:

* Single Responsibility Principle (SRP)
Saya mengimplementasikan SRP dengan memisahkan CarController ke dalam file yang berbeda dari file ProductController. Dengan begitu file Product Controller fokus mengatur interaksi terkait model Product saja dan file Car Controller fokus mengatur interaksi terkait model Car saja. 
* Open-Closed Principle (OCP)
OCP artinya kelas, modul, dan fungsi harus terbuka pada pengembangan namun tertutup terhadap modifikasi. Kode saya sudah menerapkan prinsip ini. Misalnya apabila kita ingin menambahkan mapping link yang baru kita bisa membuat file controller baru, sehingga tidak mengubah kode lama. Atau jika ingin menambahkan fungsionalitas baru, kita bisa menambahkan method pada ProductService dan CarService tanpa mengubah kode yang sudah ada.
* Liskov Substitution Principle (LSP)
Pada kode before SOLID, CarController dibuat extends Product Controller. Hal ini tidak sesuai dengan prinsip LSP karena CarController tidak dapat di-replace dengan ProductController, maupun sebaliknya tanpa mengubah alur dan kebenaran program. Karena ProductController dan CarController memang mengatur hal yang berbeda dan tidak bisa saling menggantikan, saya menghapus `extends ProductController` dari CarController.
* Interface Segregation Principle (ISP)
Dengan memisahkan interface `ProductService` dan `CarService`, program saya sudah mencegah implementasi method yang tidak dibutuhkan saat mengimplementasikan sebuah interface.
* Dependency Inversions Principle (DIP)
Pada kode before SOLID, CarController dependent pada CarServiceImpl karena menggunakan CarServiceImpl (concrete class) untuk memanggil method Service. Hal ini diperbaiki dengan menggunakan interface CarService-nya saja.
2. Manfaat menerapkan SOLID Principles<br> 
* Kode lebih mudah dipahami
* Mempermudah penambahan fitur baru karena low coupling sehingga tiap bagian kode tidak terikat pada bagian kode yang lain.
* Mengurangi risiko terjadinya bug.
* Mempermudah testing karena tiap bagian kode fokus pada tujuan tertentu.

3. Kerugian tidak mengimplementasikan SOLID Principles<br>
* Penambahan fitur sulit dilakukan.
* Kode akan sulit untuk dibaca dan diperbaiki.
Method yang melakukan terlalu banyak hal di dalamnya akan jauh lebih sulit untuk diperbaiki apabila terdapat bug di dalamnya.
* Kode akan sulit untuk diuji.
Saat kita ingin menguji suatu method, jika method yang kita buat memiliki dependency yang tinggi terhadap bagian kode lain tentu akan sulit untuk memastikan method tersebut sudah benar implementasinya.
* Kode akan sulit untuk diubah.
Jika kode kita memiliki dependency tinggi terhadap bagian kode lain, bisa-bisa saat mengubah satu bagian kode, kode-kode yang tidak berhubungan juga ikut berubah behaviornya dan terjadi error yang tidak diinginkan.
</details>

# Tutorial 4
#### Reflection
1. Menurut saya sebagai pemula, TDD cukup merepotkan karena kita harus membuat unit test sebelum
benar-benar menulis kode. Agak sulit membayangkan kasus-kasus apa yang harus dihandle jika kodingannya saja belum ada.
Namun TDD sebenarnya cukup bermanfaat. Membuat unit test di awal membuat kita fokus untuk memastikan tahap pengerjaan selanjutnya tidak
"keluar dari jalur". Dengan kata lain, unit test yang telah dibuat sebelumnya bisa memandu kita untuk fokus memenuhi dulu requirement utama dari program. 
Jumlah unit test yang telah dipenuhi pada setiap tahap pengembangan kode juga memberi gambaran sudah seberapa banyak progress kita. Ke depannya,
mungkin saya perlu lebih banyak mencari tahu kasus-kasus umum apa saja yang harus ada pada unit test agar kualitas pengerjaan kode saya saat menggunakan TDD menjadi lebih baik.


2. Prinsip F.I.R.S.T antara lain:
* Fast: Test saya sudah memenuhi prinsip ini karena terdapat pemisahan antara unitTest dan functionalTest. 
* Isolated: Test saya sudah memenuhi prinsip dengan menggunakan mock, sehingga tidak melibatkan objek asli, dan tidak bergantung pada testing lain. Saya juga sudah menggunakan setUp dalam membuat unit test. 
* Repeatable: Test saya sudah memenuhi prinsip Isolated di mana setiap data yang diperlukan di dalam test akan tetap sama setiap kali testing dilakukan, sehingga menjamin hasil yang konsisten.
* Self-Validating: Test saya sudah memenuhi prinsip ini karena menggunakan assert ketimbang "print" atau metode validasi manual lain.
* Thorough: Test saya sudah memenuhi prinsip ini karena test saya sudah mencakup unhappy dan happy paths yang terpikirkan. 