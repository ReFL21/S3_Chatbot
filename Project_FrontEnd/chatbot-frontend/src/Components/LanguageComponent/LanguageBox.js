import Stack from '@mui/material/Stack';
import TranslateIcon from '@mui/icons-material/Translate';

const LanguageBoxComponent = (props) =>{
  //Popup with divs
  const handleClick = (event) => {    
    if(document.getElementById("popover-lang").style.display === "none"){
      document.getElementById("popover-lang").style.display = "flex";    
      //actions
      document.getElementById("popover-sound").style.display = "none";
      props.func("language")
    }    
  else {
    document.getElementById("popover-lang").style.display = "none";
    //actions
    props.func("messageScreen");
  } 
  };

    return (
        <div>
            <div variant="contained" onClick={handleClick}>
        <TranslateIcon />
      </div>
      <div id='popover-lang' style={{display: "none"}}>
        <Stack spacing={2} direction="column" alignItems="center">           
      </Stack>
      </div>
        </div>
    )
}

export default LanguageBoxComponent