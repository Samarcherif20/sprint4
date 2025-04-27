package com.example.makeup;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.makeup.entities.BrandLine;
import com.example.makeup.entities.Makeup;
import com.example.makeup.repos.MakeupRepository;
import com.example.makeup.service.MakeupService;

@SpringBootTest
class MakeupApplicationTests {

    @Autowired
    private MakeupRepository makeupRepository;

    @Autowired
    private MakeupService makeupService;

    @Test
    public void testCreateMakeup() {
        Makeup makeup = new Makeup("font de teint", "channel", "visage", 188.50);
        makeupRepository.save(makeup);
    }

    @Test
    public void testFindMakeup() {
        Makeup m = makeupRepository.findById(1L).get();
        System.out.println(m);
    }

    @Test
    public void testUpdateMakeup() {
        Makeup m = makeupRepository.findById(1L).get();
        m.setPrix(50.55);
        makeupRepository.save(m);
    }

    @Test
    public void testDeleteMakeup() {
        makeupRepository.deleteById(1L);
    }

    @Test
    public void testListerTousMakeups() {
        List<Makeup> makeups = makeupRepository.findAll();
        for (Makeup m : makeups) {
            System.out.println(m);
        }
    }

    @Test
    public void testFindAllMakeupsByPage() {
        Page<Makeup> makeupsPage = makeupService.getAllMakeupParPage(0, 2);  // Correct method name
        System.out.println("Page size: " + makeupsPage.getSize());
        System.out.println("Total elements: " + makeupsPage.getTotalElements());
        System.out.println("Total pages: " + makeupsPage.getTotalPages());
        makeupsPage.getContent().forEach(makeup -> {
            System.out.println(makeup.toString());
        });
    }
    @Test
    public void testFindMakeupByNom() {
        List<Makeup>Makeups = makeupRepository.findByNom("font de teint");
        for (Makeup m : Makeups) {
            System.out.println(m);
    }
}
    @Test
    public void testFindMakeupByNomContains() {
        List<Makeup>Makeups = makeupRepository.findByNomContains("rouge");
        for (Makeup m : Makeups) {
            System.out.println(m);}}
    @Test
    public void testfindByNomPrix() {
        List<Makeup> Makeups = makeupRepository.findByNomPrix("Eyeliner", 8.55);
        for (Makeup m : Makeups) {
            System.out.println(m);
        }
    }
    @Test
    public void testfindByBrand()
    {
    BrandLine brd = new BrandLine();
    brd.setIdLigneMarque(1L);;
    List<Makeup> Makeups = makeupRepository.findByBrand(brd);
    for (Makeup m : Makeups) {
        System.out.println(m);
    }
    }
    @Test
    public void findByBrandID() {
        List<Makeup> makeups = makeupRepository.findByBrandLineIdLigneMarque(1L); 
        for (Makeup m : makeups) {
            System.out.println(m);
        }
    }
    @Test
    public void testfindByOrderByNomAsc()
    {
    List<Makeup> Makeups =
    makeupRepository.findByOrderByNomAsc();
    for (Makeup m : Makeups)
    {
    System.out.println(m);
    }
    }
    @Test
    public void testTrierMakeupNomPrix()
    {
    List<Makeup> Makeups = makeupRepository.trierMakeupNomsPrix();
    for (Makeup m : Makeups)
    {
    System.out.println(m);
    }
    }

}



















