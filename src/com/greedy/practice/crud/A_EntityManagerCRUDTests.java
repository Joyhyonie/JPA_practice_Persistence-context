package com.greedy.practice.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class A_EntityManagerCRUDTests {

	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	@BeforeAll
	public static void initFectory() {
		entityManagerFactory = Persistence.createEntityManagerFactory("jpatest"); /* xml 파일에 작성한 persistence-unit의 이름을 입력 */
	}
	
	@BeforeEach
	public void initManager() {
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@AfterAll
	public static void closeFactory() {
		entityManagerFactory.close();
	}
	
	@AfterEach
	public void closeManager() {
		entityManager.close();
	}
	
	@Test
	public void 카테고리코드로_카테고리_조회_테스트() {
		
		// given
		int categoryCode = 4;
		
		// when
		Category foundCategory = entityManager.find(Category.class, categoryCode);
		
		// then
		assertNotNull(foundCategory);
		assertEquals(categoryCode, foundCategory.getCategoryCode());
		System.out.println("foundCategory : " + foundCategory);
	}
	
	@Test
	public void 새로운_카테고리_추가_테스트() {
		
		// given
		Category category = new Category();
		category.setCategoryName("지중해");
		category.setRefCategoryCode(1);
		
		// when
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			entityManager.persist(category);
			transaction.commit();
		} catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
		// then
		assertTrue(entityManager.contains(category));
	}
	
	@Test
	public void 상위카테고리_코드_수정_테스트() {
		
		// given
		Category category = entityManager.find(Category.class, 21);
		System.out.println("category : " + category);
		
		int refCategoryCodeToChange = 3;
		
		// when
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			category.setRefCategoryCode(refCategoryCodeToChange);
			transaction.commit();
		} catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
		// then
		assertEquals(refCategoryCodeToChange, entityManager.find(Category.class, 21).getRefCategoryCode());
		
	}
	
	@Test
	public void 카테고리_삭제_테스트() {
		
		// given
		Category categoryToRemove = entityManager.find(Category.class, 21);
		
		// when
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {
			entityManager.remove(categoryToRemove);
			transaction.commit();
		} catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
		// then
		Category removedCategory = entityManager.find(Category.class, 21);
		assertEquals(null, removedCategory);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
