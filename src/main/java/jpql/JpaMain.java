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
/*
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
*/
            // 프로젝션
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            entityManager.persist(member);
//
//            entityManager.flush();
//            entityManager.clear();

//            List<Member> resultList = entityManager.createQuery("select m from Member m", Member.class)
//                    .getResultList();
//            Member findMember = resultList.get(0);
//            findMember.setAge(30);
            //1.
//            List<Member> resultList = entityManager.createQuery("select m.username, m.age from Member m")
//                    .getResultList();
//            Object o = resultList.get(0);
//            Object[] result = (Object[]) o;
//            System.out.println("username = " + result[0]);
//            System.out.println("age = " + result[1]);

            //2.
//            List<Object[]> resultList = entityManager.createQuery("select m.username, m.age from Member m")
//                    .getResultList();
//            Object[] result = resultList.get(0);
//            System.out.println("username = " + result[0]);
//            System.out.println("age = " + result[1]);

            //3.
//            List<MemberDTO> resultList = entityManager.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//            MemberDTO memberDTO = resultList.get(0);
//            System.out.println("username = " + memberDTO.getUsername());
//            System.out.println("age = " + memberDTO.getAge());

            // 페이징
/*
            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setUsername("member"+i);
                member.setAge(i);
                entityManager.persist(member);
            }

            entityManager.flush();
            entityManager.clear();
            List<Member> resultList = entityManager.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("resultList.size() = " + resultList.size());
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }
*/
            // 조인
/*
            Team team = new Team();
            team.setName("teamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(20);
            entityManager.persist(member);

            member.setTeam(team);

            entityManager.flush();
            entityManager.clear();
*/
/*
            // inner
            //List<Member> resultList = entityManager.createQuery("select m from Member m inner join m.team t", Member.class)
            //        .getResultList();

            //outer
            //List<Member> resultList = entityManager.createQuery("select m from Member m left outer join m.team t", Member.class)
            //        .getResultList();

            //세타
            //List<Member> resultList = entityManager.createQuery("select m from Member m, Team t where m.username = t.name")
            //        .getResultList();

            //String query = "select m from Member m left join m.team t on t.name = 'teamA'";
            //List<Member> resultList = entityManager.createQuery(query, Member.class)
            //        .getResultList();

            //System.out.println("resultList = " + resultList.size());

            //String query = "select m from Member m left join Team t on m.username = t.name";
            //List<Member> resultList = entityManager.createQuery(query, Member.class)
            //        .getResultList();

            //System.out.println("resultList = " + resultList.size());
*/

            //서브 쿼리



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
