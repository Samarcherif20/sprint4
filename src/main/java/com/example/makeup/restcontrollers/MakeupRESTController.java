package com.example.makeup.restcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.makeup.dto.MakeupDTO;
import com.example.makeup.entities.Makeup;
import com.example.makeup.service.MakeupService;
@RestController
@RequestMapping("/api")
@CrossOrigin
public class MakeupRESTController {
@Autowired
MakeupService makeupService;
@RequestMapping(method = RequestMethod.GET)
public List<MakeupDTO> getAllMakeup() {
	return makeupService.getAllMakeup();
}
@RequestMapping(value="/{id}",method = RequestMethod.GET)
public MakeupDTO getMakeup(@PathVariable("id") Long id) {
	return makeupService.getMakeup(id);
 }
@RequestMapping(method = RequestMethod.POST)
public MakeupDTO createMakeup(@RequestBody MakeupDTO m) {
	return makeupService.saveMakeup(m);
}
@RequestMapping(method = RequestMethod.PUT)
public MakeupDTO updateMakeup(@RequestBody MakeupDTO m) {
	return makeupService.updateMakeup(m);
}
@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
public void deleteMakeup(@PathVariable("id") Long id)
{
	makeupService.deleteMakeupById(id);

}
@RequestMapping(value="/Brand/{idbrd}",method = RequestMethod.GET)
public List<Makeup> findByBrandLineIdLigneMarque(@PathVariable("idbrd") Long idbrd) {
return makeupService.findByBrandLineIdLigneMarque(idbrd);
}


}