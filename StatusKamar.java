
/**
 * Enumeration class StatusKamar - berisi status-status kamar pada JHotel.
 *
 * @author Luthfi Musthafa_1506673656
 * @version 2018.03.10
 */
public enum StatusKamar
{
    Booked("Booked"), Processed("Processed"), Vacant("Vacant");
    
    private String status;
    
    StatusKamar(String status)
    {
        this.status = status;
    }
    
    public String toString()
    {
        return status;
    }
}
