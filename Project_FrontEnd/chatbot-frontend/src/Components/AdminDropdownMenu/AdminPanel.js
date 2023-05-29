import { useState } from "react";
import { getNodes } from "../../Services/CategoryService";
import { useEffect } from "react";
import AddEmptyModal from "./AddEmptyModel";
import ChildAccordion from "./ChildAccordion";

const AdminDropdownMenuSpaced = () => {


    let [nodes, setNodes] = useState([]);
    let [selectedItem, setSelectedItem] = useState("");
    const [parentId, setParentId] = useState(null);

    const getAllData = () => {
        getNodes().then(response => {
            setNodes(response.data.nodes);
        })
    }
    useEffect(() => {
        getAllData();
    }, []);

    const [showAddTopic, setShowAddTopic] = useState(false);

    const handleShowAddTopic = (value) => {
        setParentId(value)
        setSelectedItem(value);
        setShowAddTopic(true);
    }

    const addNodeToList = (jsonData) => {
        console.log(jsonData);

        console.log(nodes)
        nodes.concat(jsonData);
        console.log(nodes)
    }

    const [showEditChildTopic, setShowEditChildTopic] = useState(false);

    return (
        <div>

            <div id="accordion">
                {nodes.map((node, index) => {//parent            

                    if (node.parentId == null) {

                        return <ChildAccordion
                            showEditchildtopics={showEditChildTopic} setShowEditChildTopic={setShowEditChildTopic}
                            selectedItem={node} setSelectedItem={setSelectedItem}
                            childTopic={node} setNodes={setNodes}
                            nodes={nodes}
                            setShowAddTopic={setShowAddTopic} showAddTopic={showAddTopic}
                        />
                    }



                })}
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <h5 class="mb-0 moreBtn">

                            <button class="btn-light moreBtn" onClick={() => handleShowAddTopic(nodes.lastIndexOf("other"))}>+ Add more ||+ Voeg meer toe  </button>
                            {(() => {
                                if (showAddTopic == true) {
                                    return <AddEmptyModal setShowAddTopic={setShowAddTopic} showAddTopic={showAddTopic}
                                        parentId={selectedItem} setParentId={setSelectedItem} nodes={nodes} setNodes={setNodes} />;
                                }
                            })()}

                        </h5>
                    </div>
                </div>
            </div>
        </div>
    );

}

export default AdminDropdownMenuSpaced;