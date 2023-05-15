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
/*
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
*/

            // 패치 조인
            Member member1 = new Member();
            Member member2 = new Member();
            Member member3 = new Member();
            Team teamA = new Team();
            Team teamB = new Team();
            teamA.setName("teamA");
            teamB.setName("teamB");

            member1.setUsername("회원1");
            member1.setTeam(teamA);
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            member3.setUsername("회원3");
            member3.setTeam(teamB);

            entityManager.persist(teamA);
            entityManager.persist(teamB);
            entityManager.persist(member1);
            entityManager.persist(member2);
            entityManager.persist(member3);

            entityManager.flush();
            entityManager.clear();

            //String query = "Select m from Member m"; 이걸 join fetch를 써서 한방에 끝낼 수 있음

//            String query = "Select m from Member m join fetch m.team";
//            List<Member> memberList = entityManager.createQuery(query, Member.class).getResultList();
//            for (Member member : memberList) {
//                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
//            }

//            String query = "Select distinct t from Team t join fetch t.members";
//            List<Team> memberList = entityManager.createQuery(query, Team.class).getResultList();
//            for (Team team : memberList) {
//                System.out.println("team = " + team.getName() + " | members = " + team.getMembers().size());
//            }

            // 엔티티 직접 사용
            //String queryEntity = "Select m from Member m where m = :member";
            String queryId = "Select m from Member m where m.id = :memberId";
            Member findMember = entityManager.createQuery(queryId, Member.class)
                    //.setParameter("member", member1)
                    .setParameter("memberId", member1.getId())
                    .getSingleResult();
            System.out.println("findMember = " + findMember);

            // 외래 키
            String queryFor = "Select m from Member m where m.team = :team";
            List<Member> memberList = entityManager.createQuery(queryFor, Member.class)
                    .setParameter("team", teamA)
                    .getResultList();
            for (Member member : memberList) {
                System.out.println("member = " + member);
            }

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
