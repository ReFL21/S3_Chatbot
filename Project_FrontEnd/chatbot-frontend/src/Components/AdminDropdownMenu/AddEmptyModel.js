import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import { useState } from "react";
import { addNode, getNodeById } from '../../Services/CategoryService';
import SaveIcon from '@mui/icons-material/Save';
import LogoutIcon from '@mui/icons-material/Logout';


const AddEmptyModal = (props) =>{
    const [question, setQuestion] = useState("");
    const [answer, setAnswer] = useState("");
    let [vraag, setVraag] = useState("");
    let [antwoord, setAntwoord] = useState("");
    const [childId, setChildId] = useState(0);
    const [parentId, setParentId] = useState(props.parentId);     
    
    const handleCloseAddTopic = () => {
       
        props.setShowAddTopic(false);
        setQuestion("");
        setAnswer("");
    }

    const addNewNode = () =>{
        let jsonData = {
            nodeText: question,
            answer: answer,
            vraag: vraag,
            antwoord: antwoord,
            parentId: parentId.id
        }
        console.log(jsonData)

        addNode(jsonData).then((response) => {
            if(response.status === 201){
                getNodeById(response.data.nodeId).then((response) => {
                    props.nodes.push(response.data);
                    props.setNodes(props.nodes);
                    handleCloseAddTopic();
                });
            }
        });

    }
    
    return (
        
        <Modal background-color="transparent" show={props.showAddTopic} key={props} onHide={handleCloseAddTopic}>
            <Modal.Header closeButton>
                <Modal.Title>Add Topic || Voeg onderwerp toe </Modal.Title>
            </Modal.Header>
            <Modal.Body> <form>
                   
                <div class="form-outline mb-4">
                    <label class="form-label" htmlfor="form2Example1">Question</label>
                    <input type="text" id="form2Example1" value={question} onChange={(e) => setQuestion(e.target.value)} class="form-control" />
                </div>
                <div class="form-outline mb-4">
                    <label class="form-label" htmlfor="form2Example1">Answer</label>
                    <textarea type="text" id="form2Example1" value={answer} onChange={(e) => setAnswer(e.target.value)} class="form-control" />
                </div>
                <div class="form-outline mb-4">
            <label class="form-label" htmlfor="form2Example1">Vraag</label>
            <input type="text" id="form2Example1" value={vraag} onChange={(e) => setVraag(e.target.value)} class="form-control" />
        </div>
        <div class="form-outline mb-4">
            <label class="form-label" htmlfor="form2Example1">Antwoord</label>
            <textarea id="form2Example1" value={antwoord} onChange={(e) => setAntwoord(e.target.value)} class="form-control" />
        </div>
                <div class="form-outline mb-4">
                    <input type="hidden" id="form2Example1" value={childId} onChange={(e) => setChildId(e.target.value)} class="form-control" />
                </div>
                <div class="form-outline mb-4">
                    <input type="hidden" id="form2Example1" value={props.parentId.id} onChange={(e) => props.setParentId(e.target.value)} class="form-control" />
                </div>


            </form></Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={()=>addNewNode()}>
                    <SaveIcon />
                </Button>
                <Button variant="secondary" onClick={handleCloseAddTopic}>
                    <LogoutIcon />
                </Button>
            </Modal.Footer>
        </Modal>);
}

export default AddEmptyModal;