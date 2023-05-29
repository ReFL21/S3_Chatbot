import './LanguageBox.css';
import { Button } from "react-bootstrap";
import CheckIcon from '@mui/icons-material/Check';
import CloseIcon from '@mui/icons-material/Close';

const LanguageComponent = (props) =>{

    function handleClick(e){
        console.log(e);
        props.setcurrent(e);
    }


    return(
        <div className='langBoxContainer' key={props.languageName}>
           {props.languageName}
            <button className="langSelectionBtn" onClick={()=>handleClick(props.langShort)}> {props.currentLang.includes(props.langShort,0) ? <CheckIcon /> :   <CloseIcon />   }
            </button>
        </div>
    )

}

export default LanguageComponent;