package org.jpql;

import javax.persistence.*;
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

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            member.changeTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member.setUsername("관리자2");
            member.setAge(60);
            member.setType(MemberType.USER);
            member.changeTeam(team);
            em.persist(member2);


            em.flush();
            em.clear();
            String query = "select m from Member m left join Team t on m.username = t.name";
            List<Member> resultList = em.createQuery(query, Member.class)
                    .getResultList();

            String query2 = "select m.username  , 'HELLO' , TRUE from Member m "+
                            "where m.age between 0 and 10";
            List<Object[]> resultList1 = em.createQuery(query2)
                    .getResultList();

            for(Object[] objects : resultList1){
                System.out.println("objects = " + objects[0]);
                System.out.println("objects = " + objects[1]);
                System.out.println("objects = " + objects[2]);
            }
            
            String query3 = 
                    "select " +
                            "case when m.age <= 10 then '학생요금'" + 
                            "     when m.age >= 60 then '경로요금'" +
                            "     else '일반요금' " +
                            "end " +
                    "from Member m";
            List<String> resultList2 = em.createQuery(query3, String.class).getResultList();
            for(String s : resultList2){
                System.out.println("s = " + s);
            }

            String query4 = "select coalesce(m.username , '이름 없는 회원') from Member m";
            List<String> resultList3 = em.createQuery(query4, String.class).getResultList();
            for(String s : resultList3){
                System.out.println("s = " + s);
            }

            String query5 = "select nullif(m.username , '관리자') from Member m";
            List<String> resultList4 = em.createQuery(query5, String.class).getResultList();
            for(String s : resultList4){
                System.out.println("s = " + s);
            }


            String query6 = "select 'a' || 'b' from Member m ";
            String query7 = "select concat('a','b') from Member m ";
            List<String> resultList5 = em.createQuery(query7, String.class).getResultList();
            for(String s : resultList5){
                System.out.println("s = " + s);
            }

            String query8 = "select locate('de' , 'abcdegf') from Member m ";
            List<Integer> resultList6 = em.createQuery(query8, Integer.class).getResultList();
            for(Integer s : resultList6){
                System.out.println("s = " + s);
            }

            String query9 = "select size(t.members) from Team t ";
            List<Integer> resultList7 = em.createQuery(query9, Integer.class).getResultList();
            for(Integer s : resultList7){
                System.out.println("s = " + s);
            }

            String query10 = "select function('group_concat' , m.username) from Member m ";
            List<String> resultList8 = em.createQuery(query10, String.class).getResultList();
            for(String s : resultList8){
                System.out.println("s = " + s);
            }

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
