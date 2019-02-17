package com.smoothstack.Idao;


import org.springframework.data.jpa.repository.JpaRepository;


import com.smoothstack.entity.Book;

public interface IBookDao extends JpaRepository<Book, Long> {

	//@Query("select bookId,title,authId,pubId from tbl_book,tbl_author where tbl_book.authId=tbl_author.authorId and tbl_book.bookId in (select bookId from tbl_book_copies where tbl_book_copies.branchId=?1 and tbl_book_copies.noOfCopies > 0)")
	//List<Book> getAllBooksWithAtLeastOneCopie(int branchId);

}
