

import image from '../../../assests/images/Omer.png'
import Data from '../../data';

function TopBarRightSlide({image}) {
    const { currentUser } = Data();
    return (
        <>
                        <nav class="navbar navbar-expand-lg navbar-expand-md navbar-expand-sm navbar-light dark-blue1 top-chat small-nav">
                            <span class="topBarRightSlide">
                                <a href="#">
                                    <img src={currentUser.image} class="rounded-circle pic_left" alt="Your Image"></img>
                                </a>
                            </span>

                            <div class="container-fluid small-screen">
                                    <div class="col-10" >
                                        <div>
                                            <div class="row">
                                                <div class="col-12">
                                                    <span class="text-light">Me</span>
                                                </div>
                                                <div class="col-12">
                                                    <span class="text-light opacity">Online</span>
                                                </div>
                                            </div>
                                        </div>
            
                                    </div>
                                    <div class="col-1"> 
                                        <span>
                                            <svg xmlns="http://www.w3.org/2000/svg"  width="22" height="22" fill="currentColor" class="bi bi-search mx-4" viewBox="0 0 16 16">
                                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                                            </svg>
                                        </span>

                                    </div>
                                    <div class="col-1"> 
                                        <span>
                                            <svg xmlns="http://www.w3.org/2000/svg"  width="22" height="22" fill="currentColor"
                                            class="bi bi-three-dots-vertical mx-3" viewBox="0 0 16 16">
                                            <path
                                                d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z" />
                                            </svg>
                                        </span>

                                    </div>
                            </div>
                        </nav>
        </>
    );
}
export default TopBarRightSlide;
