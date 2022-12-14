package org.jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class JpaMain {

     public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);
            Team team2 = new Team();
            team2.setName("teamB");
            em.persist(team2);
            Team team3 = new Team();
            team3.setName("teamC");
            em.persist(team3);

            Member member = new Member();
            member.setUsername("회원1");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            member.changeTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setAge(60);
            member2.setType(MemberType.USER);
            member2.changeTeam(team);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원2");
            member3.setAge(60);
            member3.setType(MemberType.USER);
            member3.changeTeam(team2);
            em.persist(member3);


            em.flush();
            em.clear();
//            String query = "select m from Member m left join Team t on m.username = t.name";
//            List<Member> resultList = em.createQuery(query, Member.class)
//                    .getResultList();
//
//            String query2 = "select m.username  , 'HELLO' , TRUE from Member m "+
//                            "where m.age between 0 and 10";
//            List<Object[]> resultList1 = em.createQuery(query2)
//                    .getResultList();
//
//            for(Object[] objects : resultList1){
//                System.out.println("objects = " + objects[0]);
//                System.out.println("objects = " + objects[1]);
//                System.out.println("objects = " + objects[2]);
//            }
//
//            String query3 =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금'" +
//                            "     when m.age >= 60 then '경로요금'" +
//                            "     else '일반요금' " +
//                            "end " +
//                    "from Member m";
//            List<String> resultList2 = em.createQuery(query3, String.class).getResultList();
//            for(String s : resultList2){
//                System.out.println("s = " + s);
//            }
//
//            String query4 = "select coalesce(m.username , '이름 없는 회원') from Member m";
//            List<String> resultList3 = em.createQuery(query4, String.class).getResultList();
//            for(String s : resultList3){
//                System.out.println("s = " + s);
//            }
//
//            String query5 = "select nullif(m.username , '관리자') from Member m";
//            List<String> resultList4 = em.createQuery(query5, String.class).getResultList();
//            for(String s : resultList4){
//                System.out.println("s = " + s);
//            }
//
//
//            String query6 = "select 'a' || 'b' from Member m ";
//            String query7 = "select concat('a','b') from Member m ";
//            List<String> resultList5 = em.createQuery(query7, String.class).getResultList();
//            for(String s : resultList5){
//                System.out.println("s = " + s);
//            }
//
//            String query8 = "select locate('de' , 'abcdegf') from Member m ";
//            List<Integer> resultList6 = em.createQuery(query8, Integer.class).getResultList();
//            for(Integer s : resultList6){
//                System.out.println("s = " + s);
//            }
//
//            String query9 = "select size(t.members) from Team t ";
//            List<Integer> resultList7 = em.createQuery(query9, Integer.class).getResultList();
//            for(Integer s : resultList7){
//                System.out.println("s = " + s);
//            }
//
//            String query10 = "select function('group_concat' , m.username) from Member m ";
//            List<String> resultList8 = em.createQuery(query10, String.class).getResultList();
//            for(String s : resultList8){
//                System.out.println("s = " + s);
//            }
//
//            String query11 = "select t.members.size from Team t";
//            List<Integer> resultList9 = em.createQuery(query11, Integer.class).getResultList();
//            for(Integer i : resultList9){
//                System.out.println("i = " + i);
//            }
//
//            String query12 = "select t.members from Team t";
//            List<Collection> resultList10 = em.createQuery(query12, Collection.class).getResultList();
//
//            System.out.println("resultList10 = " + resultList10);
//
//            String query13 = "select m from Team t join t.members m";
//            List<Member> resultList11 = em.createQuery(query13, Member.class).getResultList();
//
//            System.out.println("resultList11 = " + resultList11);
//
//            String query14 = "select m from Member m";
//            List<Member> resultList12 = em.createQuery(query14, Member.class).getResultList();
//
//            for(Member m : resultList12){
//                System.out.println("member = " + m.getUsername() + " " + m.getTeam().getName());
//            }
//
//            String query15 = "select m from Member m left join fetch m.team";
//            List<Member> resultList13 = em.createQuery(query15, Member.class).getResultList();
//
//            for(Member m : resultList13){
//                System.out.println("member = " + m.getUsername() + " " + m.getTeam().getName());
//            }
//
//
//            String query16 = "select t from Team t join fetch t.members";
//            List<Team> resultList14 = em.createQuery(query16, Team.class).getResultList();
//            for(Team t : resultList14){
//                System.out.println("team = " + t.getName() + " |members=" + t.getMembers().size());
//                for(Member m : t.getMembers()){
//                    System.out.println("->members=" + m);
//                }
//
//            }
//
//            String query17 = "select distinct t from Team t join fetch t.members";
//            List<Team> resultList15 = em.createQuery(query17, Team.class).getResultList();
//            for(Team t : resultList15){
//                System.out.println("team = " + t.getName() + " |members=" + t.getMembers().size());
//                for(Member m : t.getMembers()){
//                    System.out.println("->members=" + m);
//                }
//
//            }
//
//            //fetch를 사용 안할 시
//            String query18 = "select t from Team t join t.members m";
//            List<Team> resultList16 = em.createQuery(query18, Team.class).getResultList();
//            for(Team t : resultList16){
//                System.out.println("team = " + t.getName() + " |members=" + t.getMembers().size());
//                for(Member m : t.getMembers()){
//                    System.out.println("->members=" + m);
//                }
//
//            }
//
//            //문제
//            String query19 = "select t from Team t join fetch t.members m";
//            em.createQuery(query19 , Team.class)
//                    .setFirstResult(0)
//                    .setMaxResults(1)
//                    .getResultList();


            //collection 을 페이징 해야할 경우는 batchSize를 annotation을 사용하거나 global 설정을한다 (persistence.xml)
           /* String query20 = "select t from Team t";
            List<Team> resultList17 = em.createQuery(query20, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();
            System.out.println("result = " + resultList17.size());
            for(Team t : resultList17){
                System.out.println("team = " + t.getName() + " |members=" + t.getMembers().size());
                for(Member m : t.getMembers()){
                    System.out.println("->members=" + m);
                }
            }

            String query21 = "select m from Member m where m = :member";
            em.createQuery(query21,Member.class)
                    .setParameter("member" , member)
                    .getResultList();

            String query22 = "select m from Member m where m.team = :team";
            em.createQuery(query22 ,Member.class)
                    .setParameter("team" , team)
                    .getResultList();

            //named query
            List<Member> resultList18 = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "회원1")
                    .getResultList();

            for(Member m : resultList18){
                System.out.println("member = " + m);
            }*/

            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            System.out.println("resultCount : "+ resultCount);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();


    }


}
