import React from 'react';
import Stack from '@mui/material/Stack';
import Slider from '@mui/material/Slider';
import VolumeDown from '@mui/icons-material/VolumeDown';
import VolumeUp from '@mui/icons-material/VolumeUp';
import { Box } from '@mui/system';
import SpatialTrackingIcon from '@mui/icons-material/SpatialTracking';

const SpeakBoxComponent = ({speechVolume, container}) => {
  const [speakerValue, setSpeakerValue] = React.useState(100);
  var synth = window.speechSynthesis;  
  var voices = [];

  const handleSpeakerChange = (event, newValue) => {
    setSpeakerValue(newValue);
    speechVolume = newValue/100;
    console.log(document.getElementById("voiceVolume").children.item(2).children.item(0).value);
  };

  //Popup with divs
  const handleClick = (event) => {    
    if(document.getElementById("popover-sound").style.display === "none"){
      document.getElementById("popover-sound").style.display = "flex";    
      document.getElementById("voiceList").style.display = "flex";      
      container("messageScreen");
      
      PopulateVoices();
    }    
  else  document.getElementById("popover-sound").style.display = "none";
  };

  function PopulateVoices() {
    
    voices = synth.getVoices();
    if (document.getElementById("voiceList") != null) {      
      var selectedIndex = document.getElementById("voiceList").selectedIndex < 0 ? 0 : document.getElementById("voiceList").selectedIndex;
      document.getElementById("voiceList").innerHTML = '';
      voices.forEach((voice) => {
        var listItem = document.createElement('option');
        listItem.textContent = voice.name;
        listItem.setAttribute('data-lang', voice.lang);
        listItem.setAttribute('data-name', voice.name);
        document.getElementById("voiceList").appendChild(listItem);
      });
      document.getElementById("voiceList").selectedIndex = selectedIndex;
    }
  }


  return (

    
    <div>
      <div variant="contained" onClick={handleClick}>
        <SpatialTrackingIcon />
      </div>
      <div id='popover-sound' style={{display: "none"}}>
        <Stack spacing={2} direction="column" alignItems="center">
      Volume : <Box sx={{ width: 200 }}><Stack spacing={2} direction="row" sx={{ mb: 1 }} alignItems="center">
        <VolumeDown />
        <Slider aria-label="Volume" value={speakerValue} onChange={handleSpeakerChange} id="voiceVolume" />
        <VolumeUp />
      </Stack> 
      </Box>      
      <select className="speakBox-popuptext" id='voiceList' style={{ display: "none", width: "60%" }}></select>
      </Stack>
      </div>
    </div>



  );

}

export default SpeakBoxComponent;