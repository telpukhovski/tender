package test;

import body.Brigade;
import body.Employee;
import body.Skills;
import body.Tender;
import exception.UnsuitableOfferException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TenderTest {
    private Employee engineer1;
    private Employee engineer2;
    private Employee engineer3;
    private Employee engineer4;
    private Employee worker1;
    private Employee worker2;
    private Employee worker3;
    private Employee worker4;
    private Employee worker5;
    private Employee worker6;
    private Employee worker7;
    private Brigade firstBrigade;
    private Brigade secondBrigade;
    private Brigade thirdBrigade;
    private Tender tender;

    @Before
    public void setUp() {
        engineer1 = new Employee(new HashSet<>(List.of(Skills.FOREMAN)));
        engineer2 = new Employee(new HashSet<>(List.of(Skills.FOREMAN, Skills.DRIVER)));
        engineer3 = new Employee(new HashSet<>(List.of(Skills.WORKER, Skills.HOISTMAN)));
        engineer4 = new Employee(new HashSet<>(List.of(Skills.WORKER, Skills.DRIVER)));
        worker1 = new Employee(new HashSet<>(List.of(Skills.BRICKLAYER, Skills.ELECTRICIAN)));
        worker2 = new Employee(new HashSet<>(List.of(Skills.BRICKLAYER, Skills.ELECTRICIAN, Skills.MECHANIC)));
        worker3 = new Employee(new HashSet<>(List.of(Skills.BRICKLAYER)));
        worker4 = new Employee(new HashSet<>(List.of(Skills.MECHANIC, Skills.ELECTRICIAN)));
        worker5 = new Employee(new HashSet<>(List.of(Skills.MECHANIC, Skills.DRIVER)));
        worker6 = new Employee(new HashSet<>(List.of(Skills.HOISTMAN)));
        worker7 = new Employee(new HashSet<>(List.of(Skills.DRIVER)));
        firstBrigade = new Brigade(new BigDecimal(1000000),
                new ArrayList<>(List.of(engineer1,engineer4, worker2, worker7)));
        secondBrigade = new Brigade(new BigDecimal(900000),
                new ArrayList<>(List.of(engineer2, engineer3, worker1, worker3)));
        thirdBrigade = new Brigade(new BigDecimal(950000),
                new ArrayList<>(List.of(engineer3, worker3, worker4, worker5, worker6)));

    }

    @After
    public void tearDown() {
        engineer1 = null;
        engineer2 = null;
        engineer3 = null;
        engineer4 = null;
        worker1 = null;
        worker2 = null;
        worker3 = null;
        worker4 = null;
        worker5 = null;
        worker6 = null;
        worker7 = null;
        firstBrigade = null;
        secondBrigade = null;
        thirdBrigade = null;
    }

    @Test
    public void testChooseBrigadeSuccess() throws UnsuitableOfferException {
        tender = new Tender(new BigDecimal(1000000));
        tender.addRequirements(Skills.FOREMAN, 1);
        tender.addRequirements(Skills.BRICKLAYER, 1);
        tender.addRequirements(Skills.DRIVER, 1);
        Brigade actual = firstBrigade;
        Brigade expected = tender.chooseBrigade(new ArrayList<>(List.of(firstBrigade)));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChooseBrigadeSuccess2() throws UnsuitableOfferException {
        tender = new Tender(new BigDecimal(1000000));
        tender.addRequirements(Skills.FOREMAN, 1);
        tender.addRequirements(Skills.BRICKLAYER, 1);
        tender.addRequirements(Skills.DRIVER, 1);
        Brigade actual = secondBrigade;
        Brigade expected = tender.chooseBrigade(new ArrayList<>(List.of(firstBrigade, secondBrigade)));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChooseBrigadeSuccess3() throws UnsuitableOfferException {
        tender = new Tender(new BigDecimal(1000000));
        tender.addRequirements(Skills.FOREMAN, 1);
        tender.addRequirements(Skills.BRICKLAYER, 1);
        tender.addRequirements(Skills.DRIVER, 1);
        Brigade actual = secondBrigade;
        Brigade expected = tender.chooseBrigade(new ArrayList<>(List.of(firstBrigade, secondBrigade, thirdBrigade)));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChooseBrigadeSuccess4() throws UnsuitableOfferException {
        tender = new Tender(new BigDecimal(1000000));
        tender.addRequirements(Skills.WORKER, 1);
        tender.addRequirements(Skills.BRICKLAYER, 1);
        tender.addRequirements(Skills.DRIVER, 1);
        tender.addRequirements(Skills.MECHANIC, 2);
        Brigade actual = thirdBrigade;
        Brigade expected = tender.chooseBrigade(new ArrayList<>(List.of(firstBrigade, secondBrigade, thirdBrigade)));
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = UnsuitableOfferException.class)
    public void testChooseBrigadeFailedForRequirements() throws UnsuitableOfferException {
        tender = new Tender(new BigDecimal(1000000));
        tender.addRequirements(Skills.FOREMAN, 2);
        tender.addRequirements(Skills.MECHANIC, 1);
        tender.addRequirements(Skills.DRIVER, 1);
        tender.addRequirements(Skills.ELECTRICIAN, 3);
        tender.chooseBrigade(new ArrayList<>(List.of(firstBrigade, secondBrigade, thirdBrigade)));
    }

    @Test(expected = UnsuitableOfferException.class)
    public void testChooseBrigadeFailedForPrice() throws UnsuitableOfferException {
        tender = new Tender(new BigDecimal(800000));
        tender.addRequirements(Skills.FOREMAN, 1);
        tender.addRequirements(Skills.MECHANIC, 1);
        tender.addRequirements(Skills.DRIVER, 1);
        tender.chooseBrigade(new ArrayList<>(List.of(firstBrigade, secondBrigade)));
    }
}