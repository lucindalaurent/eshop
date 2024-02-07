Nama: Lucinda Laurent<br>
NPM: 2206024745<br>
Kelas: Adpro B<br>

# Tutorial 1
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





### Reflection 2
1. Setelah menulis unit test, saya merasa senang karena sudah menyelesaikan tugasnya :) Saya juga merasa lebih yakin dengan kebenaran kode yang telah saya buat karena selain testing manual, kode saya juga lulus semua unit test yang saya buat sendiri.<br>
Jumlah unit test yang harus dibuat sebaiknya menyesuaikan jumlah fungsionalitas program yang dibuat. Fungsionalitas tersebut salah satunya bisa dilihat dari jumlah method yang ada, mulai dari constructor, getter, setter, maupun fitur-fitur seperti create, delete, edit, dan lain-lain.<br>
Untuk memastikan unit test yang dibuat sudah cukup untuk memverifikasi program kita, kita perlu mencoba menghandle semua kemungkinan interaksi yang bisa dilakukan pengguna dengan program kita. Penggunaan konsep code coverage dapat membantu kita untuk memastikan kita sudah melakukan tes pada keseluruhan program.<br>
Di sisi lain, 100% code coverage tidak menjamin program kita bebas dari bug dan error. Code coverage hanya menggambarkan berapa persen source code yang telah dites, sehingga dapat membantu kita mengecek apakah ada bagian program yang belum dites. Sayangnya code coverage tidak memberi tahu kita apakah tes yang kita buat sudah cukup baik. Bisa saja program kita sudah lulus unit tes yang kita buat, namun masih terdapat logic error pada alur program atau ada skenario yang tidak terpikirkan. <br>
2. Jika kita membuat kelas _functional test_ baru yang serupa dengan kelas sebelumnya, kode tersebut menurut saya akan jadi kurang bersih karena bersifat _redundant_. 2 kode yang serupa tentu kurang efisien dan cenderung sulit di-maintain karena jika kita ingin mengubah setupnya, kita perlu mengubahnya 2 kali. Untuk membuat kodenya lebih bersih mungkin functional test baru tersebut bisa ditambahkan di kelas `CreateProductFunctionalTest` yang sudah ada. Kita bisa membuat fungsi khusus untuk instansiasi produk, sehingga fungsi testingnya bisa fokus untuk mengecek kebenaran output saja.
