package com.smoothstack.menu.administratorData;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.dao.PublisherDao;
import com.smoothstack.entity.Publisher;




public class PublisherData {

	public static void publisher(Connection conn, Scanner userInput, PublisherDao publisherDao) {

		System.out.println("--------------------------------------------------------------------------");
		while (true) {
			System.out.println("1) Add Publisher");
			System.out.println("2) Update Publisher");
			System.out.println("3) Delete Publisher");
			System.out.println("4) Quit to Previous");
			System.out.println("--------------------------------------------------------------------------");
			String inputString = userInput.nextLine();

			if (inputString.equals("1")) {
				add(conn, userInput, publisherDao);
			} else if (inputString.equals("2")) {
				update(conn, userInput, publisherDao);
			} else if (inputString.equals("3")) {
				delete(conn, userInput, publisherDao);
			} else if (inputString.equals("4")) {
				System.out.println("--------------------------------------------------------------------------");
				return;
			}
		}
	}

	private static void add(Connection conn, Scanner userInput, PublisherDao publisherDao) {

		System.out.println("--------------------------------------------------------------------------");

		List<Publisher> publishers = publisherDao.getAll(conn);
		publishers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		Publisher publisher = new Publisher();
		System.out.println("Enter Publisher Name");
		System.out.println("--------------------------------------------------------------------------");
		String publisherName = userInput.nextLine();
		System.out.println("--------------------------------------------------------------------------");
		publisher.setPublisherName(publisherName);
		System.out.println("Enter Publisher Address");
		System.out.println("--------------------------------------------------------------------------");
		String publisherAddress = userInput.nextLine();
		publisher.setPublisherAddress(publisherAddress);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter Publisher Phone");
		System.out.println("--------------------------------------------------------------------------");
		String publisherPhone = userInput.nextLine();
		publisher.setPublisherPhone(publisherPhone);
		publisherDao.add(conn, publisher);
		publishers = publisherDao.getAll(conn);
		System.out.println("--------------------------------------------------------------------------");
		publishers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
	}

	private static void update(Connection conn, Scanner userInput, PublisherDao publisherDao) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Choose an Publisher to update");
		System.out.println("--------------------------------------------------------------------------");
		List<Publisher> publishers = publisherDao.getAll(conn);
		publishers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter Publisher Id");
		System.out.println("--------------------------------------------------------------------------");
		int publisherBranchId = Integer.parseInt(userInput.nextLine());
		System.out.println("--------------------------------------------------------------------------");
		Publisher publisher = publisherDao.getById(conn, publisherBranchId);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Enter new Publisher Name");
		System.out.println("--------------------------------------------------------------------------");
		String publisherName = userInput.nextLine();
		System.out.println("--------------------------------------------------------------------------");
		publisher.setPublisherName(publisherName);
		System.out.println("Enter new Publisher Address");
		System.out.println("--------------------------------------------------------------------------");
		String publisherAddress = userInput.nextLine();
		publisher.setPublisherAddress(publisherAddress);
		System.out.println("Enter new Publisher Phone");
		System.out.println("--------------------------------------------------------------------------");
		String publisherPhone = userInput.nextLine();
		publisher.setPublisherPhone(publisherPhone);
		publisherDao.update(conn, publisher);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Publisher " + publisher + " was successfully updated");
		System.out.println("--------------------------------------------------------------------------");
		publishers = publisherDao.getAll(conn);
		publishers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");

	}

	private static void delete(Connection conn, Scanner userInput, PublisherDao publisherDao) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Choose an LibraryBranch to delete  by Id");
		System.out.println("--------------------------------------------------------------------------");
		List<Publisher> publishers = publisherDao.getAll(conn);
		publishers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
		int LibraryBranchId = Integer.parseInt(userInput.nextLine());
		publisherDao.delete(conn, LibraryBranchId);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("LibraryBranch with id " + LibraryBranchId + " was successfully deleted");
		System.out.println("--------------------------------------------------------------------------");
		publishers = publisherDao.getAll(conn);
		publishers.stream().sorted((a, b) -> a.getId() - b.getId()).forEach(b -> System.out.println(b));
		System.out.println("--------------------------------------------------------------------------");
	}
}
