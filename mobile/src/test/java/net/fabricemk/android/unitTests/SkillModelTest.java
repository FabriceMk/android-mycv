package net.fabricemk.android.unitTests;

import net.fabricemk.android.mycv.models.Skill;
import net.fabricemk.android.mycv.models.SkillList;
import net.fabricemk.android.mycv.models.SkillSubset;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/*
    Just a small class to test some interactions between the
    model classes related to skills. As the model architecture is really simple,
    this test class is just here to have a JUnit testing workflow
 */
public class SkillModelTest {

    @Before
    public void beforeTest() {
        System.out.println("---------- START --------------");
    }

    @After
    public void afterTest() {
        System.out.println("----------  END  --------------");
    }

    @BeforeClass
    public static void initFixtures() {

    }

    @Test
    public void testModel() {
        // Skills creation
        Skill skill1 = new Skill();
        skill1.setName("Skill 1");

        Skill skill2 = new Skill();
        skill2.setName("Skill 2");

        Skill skill3 = new Skill();
        skill3.setName("Skill 3");

        Skill skill4 = new Skill();
        skill4.setName("Skill 4");

        Skill skill5 = new Skill();
        skill5.setName("Skill 5");

        // Subsets creation
        SkillSubset skillSubset1 = new SkillSubset();
        skillSubset1.setSubsetName("BBB_Subset");

        SkillSubset skillSubset2 = new SkillSubset();
        skillSubset2.setSubsetName("AAA_Subset");

        skillSubset1.addSkill(skill2);
        skillSubset1.addSkill(skill3);
        assertEquals(2, skillSubset1.getSkills().size());

        skillSubset2.addSkill(skill1);
        skillSubset2.addSkill(skill4);
        skillSubset2.addSkill(skill5);
        assertEquals(3, skillSubset2.getSkills().size());

        List<SkillSubset> temp = new ArrayList<>();
        temp.add(skillSubset1);
        temp.add(skillSubset2);

        // Test the Skill List building and order
        SkillList skillList = new SkillList();
        skillList.buildFromList(temp);

        /*
         * We have added skillSubset1 before skillSubset2
         * The SkillList object is supposed to give the Map of
         * SkillSubset in the same order when iterating
         */
        Iterator<String> iterator = skillList
                .getAllSkills().keySet().iterator();

        assertEquals("BBB_Subset", iterator.next());
        assertEquals("AAA_Subset", iterator.next());

    }

}
