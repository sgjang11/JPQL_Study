package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
            Member member = new Member();
            member.setUsername("member1");
            entityManager.persist(member);

            // 반환 타입이 명확한 경우
            TypedQuery<Member> query_1 = entityManager.createQuery("select m from Member m", Member.class);
            //TypedQuery<String> query_2 = entityManager.createQuery("select m.username from Member m", String.class);
            // 반환 타입이 명확하지 않을 경우
            //Query query_3 = entityManager.createQuery("select m.username, m.age from Member m");

            // 전체 조회
            List<Member> memberList = query_1.getResultList();

            for (Member member1 : memberList) {
                System.out.println("member1 = " + member1);
            }

            // 하나 값 조회
            TypedQuery<Member> query_4 = entityManager.createQuery("select m from Member m where m.id = 1", Member.class);
            Member singleResult = query_4.getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());

            TypedQuery<Member> query_5 = entityManager.createQuery("select m from Member m where m.username=:username", Member.class);
            query_5.setParameter("username", "member1");
            Member singleResult1 = query_5.getSingleResult();
            System.out.println("singleResult1 = " + singleResult1.getUsername());

            // 심플하게
            Member singleResult2 = entityManager.createQuery("select m from Member m where m.username=:username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult2 = " + singleResult2.getUsername());

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }

        entityManagerFactory.close();

    }

}
