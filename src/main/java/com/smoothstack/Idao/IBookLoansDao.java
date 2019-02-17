package com.smoothstack.Idao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smoothstack.entity.BookLoans;

public interface IBookLoansDao extends JpaRepository<BookLoans, Long> {

	@Query("select b from tbl_book_loans b where b.id.book.id = ?1 and b.id.libraryBranch.id = ?2 and b.id.borrower.id = ?3")
	BookLoans getByBookIdAndBranchIdAndCardNo(long bookId, long branchId, long cardNo);

	@Modifying
	@Query("delete from tbl_book_loans where book_id=:bookId and branch_id=:branchId and card_no=:cardNo")
	void delete(@Param("bookId") long bookId, @Param("branchId") long branchId, @Param("cardNo") long cardNo);

	@Modifying(clearAutomatically = true)
	@Query("update tbl_book_loans b SET b.dueDate = :dt WHERE b.id.book.id = :bookId and b.id.libraryBranch.id = :branchId and b.id.borrower.id = :cardNo")
	void updateDueDate(@Param("bookId") long bookId, @Param("branchId") long branchId, @Param("cardNo") long cardNo,
			@Param("dt") LocalDate dt);

	@Modifying
	@Query(value = "insert into tbl_book_loans  (book_id, branch_id, card_no, date_out, due_date) VALUES (:bookId,:branchId,:cardNo,:dateOut,:dateDue)", nativeQuery = true)
	void create(@Param("bookId") long bookId, @Param("branchId") long branchId, @Param("cardNo") long cardNo,
			@Param("dateOut") LocalDate dateOut, @Param("dateDue") LocalDate dateDue);

}
