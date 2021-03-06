package androidApi.service;

import androidApi.dto.FullReservationDTO;
import androidApi.dto.GetReservationsDTO;
import androidApi.dto.ItineraryDTO;
import androidApi.model.*;
import androidApi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService{

    @Autowired
    ReservationAccomodationRepository reservationAccomodationRepository;

    @Autowired
    ReservationFligthRepository reservationFligthRepository;

    @Autowired
    AccomodationRepository accomodationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlightsRepository flightsRepository;

    public Reservations_accomodations addReservation(Reservations_accomodations reservations_accomodations, int accId, int userId){
        Accomodations accomodations =  accomodationRepository.findOne(accId);
        User user = userRepository.findOne(userId);

        reservations_accomodations.setAccomodation(accomodations);
        reservations_accomodations.setUser(user);

        return reservationAccomodationRepository.save(reservations_accomodations);
    }

    public Reservations_flights addResFlight(Reservations_flights reservations_flights, String flightId, int userId){
        Flights flights =  flightsRepository.findByName(flightId);
        User user = userRepository.findOne(userId);

        reservations_flights.setFlight(flights);
        reservations_flights.setUser(user);

        return reservationFligthRepository.save(reservations_flights);
    }

    public GetReservationsDTO getReservationAcc(int userId){
        GetReservationsDTO getReservationsDTOS = new GetReservationsDTO();
        List<Reservations_accomodations> reservationsAcc = reservationAccomodationRepository.findAll();
        List<Reservations_flights> reservations_flights =  reservationFligthRepository.findAll();
        List<ItineraryDTO> itineraryDTOSList = new ArrayList<>();
        for (Reservations_accomodations resA: reservationsAcc){
            boolean flag = true;
            ItineraryDTO itineraryDTO = new ItineraryDTO();
            itineraryDTO.setReservations_accomodations(resA);
            for (Reservations_flights resF: reservations_flights) {
                if (parseToDateTime(resA.getBegin_time()) == parseToDateTime(resF.getFlight_date())){
                    itineraryDTO.setReservations_flights_to(resF);
                   // itineraryDTOSList.add(itineraryDTO);
                }else if(parseToDateTime(resA.getEnd_time()) == parseToDateTime(resF.getFlight_date())){
                    itineraryDTO.setReservations_flights_from(resF);
                }
            }
            if(flag && itineraryDTOSList.size() < 1 && (itineraryDTO.getReservations_flights_to() != null || itineraryDTO.getReservations_flights_from()!= null)){
                itineraryDTOSList.add(itineraryDTO);
                flag = false;
            }
        }
        getReservationsDTOS.setReservationsAccomodations(reservationsAcc);
        getReservationsDTOS.setReservations_flights(reservations_flights);
        getReservationsDTOS.setItineraryDTOS(itineraryDTOSList);

       return getReservationsDTOS;
    }

    private long parseToDateTime(String date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public void deleteReservation (int resId){
        Reservations_accomodations ra =  reservationAccomodationRepository.findOne(resId);
        reservationAccomodationRepository.delete(ra);
    }
}
