# Reuse Book
In this problem, a book has been sold and brought from and by students. 
Once a book is sold, it can be bought from the student again at discounted rate depending on year of publication and no of time book has been sold.

### End to End System :
  1) A new book is bought and register in the system. We will track the price of the book and from which student it was bought.
  2) That book will be sold to student at bought price.
  3) Again that student can sell the book to us i.e., we can buy that again, and now the id is register in our system, so we will get the last sold price and put a discount.


* User can search for book in the system, if an empty key came to the system then it will throw bad request error.
* There can be multiple entries of same book as one book can be bought new and some can be bought 2nd or 3rd time. Also, same book can be bought from different student. 


### Design
* Book table will have unique id, title, isbn, edition and is available.
* Author table will have unique id, author name and book id.
* Student table would have unique id as suid and name.
* Price table would have unique id, book id and the price.
* Type table would have unique id and name.
* Transaction table would have unique id, book id, price id, student id and type id.

* Type table would be acting as CONSTANT table in the database. 
* Whenever a book is bought, the system would have entry in book table, in price table containing the price for it and transaction table where type id would be id of bought, price id, student id from which the book is bought.
* An author would be added to the book. A book can have multiple author.
* Whenever a book is sold, it would update the is available field to 0 in the book table. Also, there would be entry in transaction table where the type id would be id of the sell.
* Whenever a book is bought again of same unique id, is available field in the book table is set to 1, will get the latest price of that book from the price table and calculate the new price and insert it into price table. Also, there would be entry in the transaction table.