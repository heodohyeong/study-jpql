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
            member.setUsername("member1");
            member.setAge(10);
            member.changeTeam(team);
            em.persist(member);



            em.flush();
            em.clear();
            String query = "select m from Member m left join Team t on m.username = t.name";
            List<Member> resultList = em.createQuery(query, Member.class)
                    .getResultList();


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
