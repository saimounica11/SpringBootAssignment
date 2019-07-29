package com.stackroute.Muzix.service;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exception.TrackAlreadyExistsException;
import com.stackroute.Muzix.exception.TrackNotFoundException;
import com.stackroute.Muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService{

    TrackRepository trackRepository;
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository){
        this.trackRepository=trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("track already exists");
        }
        Track saveTrack= trackRepository.save(track);
        if(saveTrack==null)
            throw new TrackAlreadyExistsException(" track already exists");
        return saveTrack;
    }
    @Override
    public boolean deleteTrackById(int id) throws TrackNotFoundException{
        if (getTrackById(id) == null) {
            throw new TrackNotFoundException("track not found");
        } else {
            trackRepository.delete(getTrackById(id));
            return true;
        }
    }
    @Override
    public boolean updateTrack(Track track) {
        Track updatetrack = trackRepository.findById(track.getId()).get();
        if (updatetrack != null) {
            updatetrack.setId(track.getId());
            updatetrack.setName(track.getName());
            updatetrack.setComment(track.getComment());
            trackRepository.save(updatetrack);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException{
        Optional<Track> trackbyid=trackRepository.findById(id);
        if(trackbyid.isPresent()){
            return trackbyid.get();

        }
        else {
            throw new TrackNotFoundException("track not found");
        }
    }
    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }
    @Override
    public List<Track> queryTrackByName(String name) {
        List<Track> trackList=trackRepository.queryTrackByName(name);
        return trackList;
    }
}