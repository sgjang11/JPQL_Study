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

/*
            Team team = new Team();
            team.setName("teamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(20);
            member.setType(MemberType.ADMIN);
            entityManager.persist(member);

            member.setTeam(team);

            entityManager.flush();
            entityManager.clear();
*/

//            String query = "select m.username, 'HELLO', TRUE from Member m where m.type = jpql.MemberType.ADMIN";
//            List<Object[]> resultList = entityManager.createQuery(query)
//                    .getResultList();
            // 또는
            // String query = "select m.username, 'HELLO', TRUE from Member m where m.type = :userType";

            // 이런 것도 가능
            // String query = "select m.username, 'HELLO', TRUE from Member m"
            //                 +" where m.username is not null";

            // String query = "select m.username, 'HELLO', TRUE from Member m"
            //                 +" where m.age between 0 and 10";

/*
            List<Object[]> resultList = entityManager.createQuery(query)
                    .setParameter("userType", MemberType.ADMIN)
                    .getResultList();

            for (Object[] objects : resultList) {
                System.out.println("objects = " + objects[0]);
                System.out.println("objects = " + objects[1]);
                System.out.println("objects = " + objects[2]);
            }
*/
/*
            Team team = new Team();
            team.setName("teamA");
            entityManager.persist(team);

            Member member = new Member();
            //member.setUsername("teamA");
            member.setUsername(null);
            //member.setUsername("관리자");
            member.setAge(20);
            member.setType(MemberType.ADMIN);
            entityManager.persist(member);

            member.setTeam(team);

            entityManager.flush();
            entityManager.clear();
*/

/*
            String query = "select " +
                    "case when m.age <= 10 then '학생요금' " +
                    " when m.age >= 60 then '경로요금' " +
                    " else '일반요금' " +
                    "end " +
                    " from Member m";
*/
/*
            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";

            //String query = "select NULLIF(m.username, '관리자') from Member m";

            List<String> resultList = entityManager.createQuery(query, String.class).getResultList();
            for (String s : resultList) {
                System.out.println("s = " + s);
            }
*/

            Member member1 = new Member();
            Member member2 = new Member();

            member1.setUsername("관리자1");
            member2.setUsername("관리자2");
            entityManager.persist(member1);
            entityManager.persist(member2);

            entityManager.flush();
            entityManager.clear();

            // 문자 합치기 1
            //String query = "select 'a' || 'b' from Member m";
            // 문자 합치기 2
            //String query = "select concat('a', 'b') from Member m";
            // 문자 자르기
            //String query = "select subString(m.username, 2, 3) from Member m";
            // 4를 돌려준다 (아래의 부분을 (List 부분) Integer type으로 변경해줘야함)
            //String query = "select locate('de', 'abcdefg') from Member m";
            // 컬렉션의 크기 (아래의 부분을 (List 부분) Integer type으로 변경해줘야함)
            //String query = "select size(t.members) from Team t";

            // 사용자 정의 함수
            //String query = "select function('group_concat', m.username) from Member m";
            String query = "select group_concat(m.username) from Member m";


            List<String> resultList = entityManager.createQuery(query, String.class).getResultList();
            for (String s : resultList) {
                System.out.println("s = " + s);
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
