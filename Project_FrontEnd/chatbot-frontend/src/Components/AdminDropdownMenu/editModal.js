import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import { deleteTopic, updateTopic } from '../../Services/ServiceDeskService';
import { useEffect, useState } from 'react';
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import SaveIcon from '@mui/icons-material/Save';
import LogoutIcon from '@mui/icons-material/Logout';

const EditModal = (props) => {
    const [childId, setNodeId] = useState(null);
    const [parentId, setParentId] = useState(null);
    const [nodeText, setNodeText] = useState(null);
    const [answer, setAnswer] = useState(null);
    const [antwoord, setAntwoord] = useState(null);
    const [vraag, setVraag] = useState(null);
    useEffect(() => {
        setNodeId(props.selectedItem.id)
        setParentId(props.selectedItem.parentId);
        setNodeText(props.selectedItem.nodeText);
        setAnswer(props.selectedItem.answer);
        setVraag(props.selectedItem.vraag);
        setAntwoord(props.selectedItem.antwoord);
    }, [])
    const handleCloseEditSubTopic = () => {
        props.setShowEditSubTopic(false);
        props.setSelectedItem(null);
    }
    const handleSaveSubTopic = () => {
        const updatedTopic = {
            id: childId,
            parentId: parentId,
            nodeText: nodeText,
            answer: answer,
            vraag: vraag,
            antwoord: antwoord
        };
        console.log(updatedTopic);
        console.log(props.selectedItem);
        updateTopic(childId, nodeText, answer, vraag, antwoord, parentId)
            .then(props.nodes[props.nodes.lastIndexOf(props.selectedItem)] = updatedTopic)
            .then(handleCloseEditSubTopic);

    }
    const handleDeleteSubTopic = () => {
        console.log(props)
        deleteTopic(childId)
            .then(props.setNodes(props.nodes.filter(obj => obj.id !== childId)))
            .then(handleCloseEditSubTopic)

    }

    return (<Modal background-color="transparent" show={props.showEditSubTopic} onHide={handleCloseEditSubTopic}>
        <Modal.Header closeButton>
            <Modal.Title>Edit SubTopic</Modal.Title>
        </Modal.Header>
        <Modal.Body> <form>

            <div class="form-outline mb-4">
                <label class="form-label" htmlFor="form2Example1">Question</label>
                <input type="text" id="form2Example1" defaultValue={props.selectedItem.nodeText} onChange={(e) => setNodeText(e.target.value)}/*props.setSelectedItem(e.target.value)}*/ class="form-control" />
            </div>
            <div class="form-outline mb-4">
                <label class="form-label" htmlFor="form2Example1">Answer</label>
                <textarea id="form2Example1" defaultValue={props.selectedItem.answer} onChange={(e) => setAnswer(e.target.value)}/*props.setSelectedItem(e.target.value)}*/ class="form-control" />
            </div>
            <div class="form-outline mb-4">
                <label class="form-label" htmlFor="form2Example1">Vraag</label>
                <input type="text" id="form2Example1" defaultValue={props.selectedItem.vraag} onChange={(e) => setVraag(e.target.value)}/* props.setSelectedItem(e.target.value)}*/ class="form-control" />
            </div>
            <div class="form-outline mb-4">
                <label class="form-label" htmlFor="form2Example1">Antwoord</label>
                <textarea id="form2Example1" defaultValue={props.selectedItem.antwoord} onChange={(e) => setAntwoord(e.target.value)} /*props.setSelectedItem(e.target.value)}*/ class="form-control" />
            </div>
            <div class="form-outline mb-4">
                <input type="hidden" id="form2Example1" value={props.selectedItem.id} onChange={(e) => props.setSelectedItem(e.target.value)} class="form-control" />
            </div>
            <div class="form-outline mb-4">
                <input type="hidden" id="form2Example1" value={props.selectedItem.parentId} onChange={(e) => props.setSelectedItem(e.target.value)} class="form-control" />
            </div>
        </form></Modal.Body>
        <Modal.Footer>
            <Button variant="primary" onClick={handleSaveSubTopic}>
                <SaveIcon />
            </Button>
            <Button variant="danger" onClick={handleDeleteSubTopic}>
                <DeleteForeverIcon />
            </Button>
            <Button variant="secondary" onClick={handleCloseEditSubTopic}>
                <LogoutIcon />
            </Button>

        </Modal.Footer>
    </Modal>);
}

export default EditModal;