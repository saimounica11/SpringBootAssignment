package com.stackroute.Muzix.repository;

import com.stackroute.Muzix.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {
    @Autowired
    TrackRepository trackRepository;
    Track track;
    @Before
    public void setUp()
    {
        track = new Track();
        track.setId(1);
        track.setName("Nenjinile");
        track.setComment("Awesome");
    }
    @After
    public void tearDown(){

        trackRepository.deleteAll();
    }

    @Test
    public void testSaveUser(){
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(1,fetchUser.getId());
    }
    @Test
    public void testGetAllUser() {
        Track track1 = new Track(1, "kabira", "good");
        Track track2 = new Track(2, "closer", "good");
        trackRepository.save(track1);
        trackRepository.save(track2);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("kabira", list.get(0).getName());

    }
    @Test
    public void testSaveUserFailure(){
        Track testUser = new Track(1,"shape of you","");
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testUser,track);
    }
}
