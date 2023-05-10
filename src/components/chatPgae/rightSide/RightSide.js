import imageOmer from '../../../assests/images/Omer.png';
import MessageNav from './MessageNav';
import BoxMessage from './BoxMessage';
import TopBarRightSlide from './TopBarRightSide';

function RightSide() {
    return (
            <>
                    <div class="col-8 right-slide line-up p-0 parent-div ">
                        <TopBarRightSlide/>
                        <BoxMessage />
                        <MessageNav />
                    </div>
            </>
    );
}

export default RightSide;
