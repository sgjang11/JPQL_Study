package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
            Member member1 = new Member();
            Member member2 = new Member();
            Team team = new Team();

            member1.setUsername("관리자1");
            member1.setTeam(team);
            member2.setUsername("관리자2");
            member2.setTeam(team);

            entityManager.persist(team);
            entityManager.persist(member1);
            entityManager.persist(member2);

            entityManager.flush();
            entityManager.clear();

            //String query = "select m.username from Member m";
            // 만약 member 클래스 안에 team 클래스가 호출된다면
            // 아래처럼 가능
            //String query = "select m.team from Member m";

//            List<Team> resultList = entityManager.createQuery(query, Team.class).getResultList();
//            for (Team s : resultList) {
//                System.out.println("s = " + s);
//            }

            String query = "select t.members.size from Team t";
            List<Integer> resultList = entityManager.createQuery(query, Integer.class).getResultList();
            System.out.println("resultList = " + resultList);

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
