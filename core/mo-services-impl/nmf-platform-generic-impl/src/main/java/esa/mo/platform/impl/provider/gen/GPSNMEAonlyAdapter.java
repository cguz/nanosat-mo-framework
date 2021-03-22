/* ----------------------------------------------------------------------------
 * Copyright (C) 2021      European Space Agency
 *                         European Space Operations Centre
 *                         Darmstadt
 *                         Germany
 * ----------------------------------------------------------------------------
 * System                : ESA NanoSat MO Framework
 * ----------------------------------------------------------------------------
 * Licensed under the European Space Agency Public License, Version 2.0
 * You may not use this file except in compliance with the License.
 *
 * Except as expressly set forth in this License, the Software is provided to
 * You on an "as is" basis and without warranties of any kind, including without
 * limitation merchantability, fitness for a particular purpose, absence of
 * defects or errors, accuracy or non-infringement of intellectual property rights.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * ----------------------------------------------------------------------------
 */
package esa.mo.platform.impl.provider.gen;

import esa.mo.platform.impl.util.HelperGPS;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ccsds.moims.mo.platform.gps.structures.Position;
import org.ccsds.moims.mo.platform.gps.structures.SatelliteInfoList;

/**
 *
 * @author Cesar Coelho
 */
public abstract class GPSNMEAonlyAdapter implements GPSAdapterInterface
{
  private static final Logger LOGGER = Logger.getLogger(GPSNMEAonlyAdapter.class.getName());

  @Override
  public Position getCurrentPosition()
  {
    String nmeaLog = "";
    try {
      String fullNmeaResponse = this.getNMEASentence("LOG GPGGALONG\r\n");
      nmeaLog = HelperGPS.sanitizeNMEALog(fullNmeaResponse.trim());
      if (!nmeaLog.startsWith("$GPGGA")) {
        LOGGER.log(Level.SEVERE, "Unexpected response format: {0}", nmeaLog);
      } else {
        return HelperGPS.gpggalong2Position(nmeaLog);
      }
    } catch (NumberFormatException ex1) {
      LOGGER.log(Level.SEVERE,
          "Number format exception! The gpggalong string is: " + nmeaLog, ex1);
    } catch (IOException ex) {
      LOGGER.log(Level.FINE,
          "The current position could not be retrieved! The receiver is likely offline.", ex);
    }

    return null;
  }

  @Override
  public SatelliteInfoList getSatelliteInfoList()
  {
    try {
      String nmeaLog = HelperGPS.sanitizeNMEALog(this.getNMEASentence("LOG GPGSV\r\n").trim());
      if (!nmeaLog.startsWith("$GPGSV")) {
        LOGGER.log(Level.SEVERE, "Unexpected response format: {0}", nmeaLog);
      } else {
        return HelperGPS.gpgsv2SatelliteInfoList(nmeaLog);
      }
    } catch (IOException ex) {
      LOGGER.log(Level.SEVERE, null, ex);
    }

    return null;
  }

  @Override
  public String getBestXYZSentence() throws IOException
  {
    return HelperGPS.sanitizeNMEALog(this.getNMEASentence("LOG BESTXYZ\r\n"));
  }

  @Override
  public String getTIMEASentence() throws IOException
  {
    return HelperGPS.sanitizeNMEALog(this.getNMEASentence("LOG TIMEA\r\n"));
  }
}
