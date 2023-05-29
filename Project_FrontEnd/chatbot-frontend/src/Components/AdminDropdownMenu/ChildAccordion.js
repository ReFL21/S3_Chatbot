import NodeValues from "./NodeValues";
import EditModal from "./editModal";
import AddEmptyModal from "./AddEmptyModel";
import { useState, useEffect } from "react";
import EditIcon from '@mui/icons-material/Edit';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import ThumbDownAltIcon from '@mui/icons-material/ThumbDownAlt';
import VisibilityIcon from '@mui/icons-material/Visibility';
import { getRatingById } from "../../Services/ServiceDeskService";

const ChildAccordion = (props) => {
    const [parentId, setParentId] = useState(null);
    const [showEditChildTopic, setShowEditChildTopic] = useState(false);
    const [satisfied, setSatisfied] = useState(0);
    const [unsatisfied, setUnsatisfied] = useState(0);
    const [total, setTotal] = useState(0);

    useEffect(() => {
        getRatingById(props.childTopic.id).then((response) => {
            setSatisfied(response.data.timesSatisfied);
            setUnsatisfied(response.data.timesUnsatisfied);
            setTotal(response.data.timesReached);
        })
    })
    //setSatisfied(getRatingById(props.childTopic.id))

    const handleShowEditChildTopic = (item) => {
        props.setSelectedItem(item);
        setShowEditChildTopic(true);
    }

    const handleShowAddTopic = (value) => {
        setParentId(value)
        props.setSelectedItem(value);
        props.setShowAddTopic(true);
    }
    return (
        <div className="card-body" key={props.childTopic.id}>
            <div id="accordion" >
                <div class="card">
                    <div class="card-header" id={"heading" + `${props.childTopic.id}`}>
                        <h5 class="mb-0" >
                            <button class="btn-collapsed collapsed" data-toggle="collapse" toggle="false" data-target={`#${props.childTopic.id}`} aria-expanded="false" aria-controls={props.childTopic.id}>
                                {props.childTopic.nodeText} || {props.childTopic.vraag} <ThumbUpAltIcon />{satisfied} <ThumbDownAltIcon />{unsatisfied} <VisibilityIcon /> {total}
                            </button>
                        </h5>
                    </div>
                    <div id={props.childTopic.id} class="collapse" aria-labelledby={props.childTopic.id} data-parent={"#heading" + `${props.childTopic.id}`}>
                        <div class="card-body">
                            <div>
                                <NodeValues answer={props.childTopic.answer} antwoord={props.childTopic.antwoord} />
                            </div>
                            <div className="button-holder">
                                <button class="btn-warning" onClick={() => handleShowEditChildTopic(props.childTopic)}><EditIcon /></button>
                            </div>
                            {(() => {
                                if (showEditChildTopic == true) {
                                    return <EditModal showEditSubTopic={showEditChildTopic} setShowEditSubTopic={setShowEditChildTopic} selectedItem={props.selectedItem} setSelectedItem={props.setSelectedItem} nodes={props.nodes} setNodes={props.setNodes} />;
                                }
                            })()}
                            <div className="card-body" key={props.childTopic.id}>
                                <div id="accordion">
                                    {props.nodes.map((node, index) => {
                                        if (node.parentId === props.childTopic.id)

                                            return <ChildAccordion
                                                showEditchildtopics={showEditChildTopic} setShowEditChildTopic={setShowEditChildTopic} selectedItem={node} setSelectedItem={props.setSelectedItem}
                                                childTopic={node} nodes={props.nodes} setNodes={props.setNodes}
                                                setShowAddTopic={props.setShowAddTopic} showAddTopic={props.showAddTopic}
                                            />

                                        if (index === (props.nodes.length - 1))
                                            return <div class="card">
                                                <div class="card-header" id="headingOne">
                                                    <h5 class="mb-0 moreBtn">

                                                        <button className="btn-light moreBtn " onClick={() => handleShowAddTopic(props.childTopic)}>+ Add more ||+ Voeg meer toe  </button>
                                                        {(() => {
                                                            if (props.showAddTopic === true) {
                                                                return <AddEmptyModal
                                                                    setShowAddTopic={props.setShowAddTopic} showAddTopic={props.showAddTopic}
                                                                    parentId={props.selectedItem} setParentId={props.setSelectedItem} setNodes={props.setNodes} />
                                                            }
                                                        })()}

                                                    </h5>
                                                </div>
                                            </div>

                                    })}
                                </div></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    )
}

export default ChildAccordion;